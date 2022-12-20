
package com.poly.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Sort;

import com.poly.entity.OrderDetails;
import com.poly.entity.Orders;
import com.poly.entity.Products;
import com.poly.entity.Users;
import com.poly.repository.ProductsRepo;
import com.poly.service.OrderDetailService;
import com.poly.service.OrderService;
import com.poly.service.UsersService;
import com.poly.util.SessionUtil;

@Controller
@RequestMapping("/oneshop/checkorder")
public class CheckOrderController {
	@Autowired
	private UsersService usersService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDetailService orderDetailsService;
	@Autowired
	private ProductsRepo productsrepo;
	
	//Hiển thị toàn bộ đơn hàng trong đơn hàng của bạn
	@GetMapping("/orderinfo")
	public String order(Model model,HttpSession session) {
		Users c = SessionUtil.getCurrentUser(session);
		usersService.findByUsername(c.getUsername());
		Page<Orders> listO0 = orderService.findByusername(c.getUsername(), 0,
				PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC, "id")));
		model.addAttribute("orders0", listO0);

		Page<Orders> listO1 = orderService.findByusername(c.getUsername(), 1,
				PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC, "id")));
		model.addAttribute("orders1", listO1);

		Page<Orders> listO2 = orderService.findByusername(c.getUsername(), 2,
				PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC, "id")));
		model.addAttribute("orders2", listO2);

		Page<Orders> listO3 = orderService.findByusername(c.getUsername(), 3,
				PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC, "id")));
		model.addAttribute("orders3", listO3);
		model.addAttribute("user",c);
			return "user_html/order_check";
										
	}
	//Chi tiết sản phẩm đã đặt
	@GetMapping("/detail_order/{orderid}")
	public String detail(@PathVariable("orderid") int id,Model model) {
		List<OrderDetails> orderDetails = orderDetailsService.findByOrderId(id);
		model.addAttribute("orderdetail", orderDetails);		
		model.addAttribute("orderdetaiRequest",new OrderDetails());
		return "user_html/order_details";
	}
	
    //Hủy đơn hàng
	@RequestMapping("/cancel/{order-id}")
	public String order_cancel(@PathVariable("order-id")Integer id,Model model,Orders order) throws SQLException {
		Orders o = orderService.findById(id);
		o.setStatus( 3);	
		orderService.save(o);
		Products p = null;
		List<OrderDetails> listDe = orderDetailsService.findByOrderId(id);
		for(OrderDetails od : listDe) {
			p = od.getProduct();
			p.setQuantity(p.getQuantity() + od.getQuantity());
			productsrepo.save(p);
		}
		
		return "redirect:/oneshop/checkorder/orderinfo";
	}
	
	


}
