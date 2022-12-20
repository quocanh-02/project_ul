package com.poly.controller.admin;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.entity.OrderDetails;
import com.poly.entity.Orders;
import com.poly.entity.Products;
import com.poly.repository.ProductsRepo;
import com.poly.service.OrderDetailService;
import com.poly.service.OrderService;
import com.poly.service.ProductsService;
import com.poly.service.SessionService;

@Controller
@RequestMapping("/admin/oneshop/order")
public class OrderController {
	@Autowired
	private OrderService ordersService;
	@Autowired
	private OrderDetailService orderDetailsService;
	@Autowired
	private ProductsService productsService;
	@Autowired
	private ProductsRepo productsrepo;
	@Autowired
	SessionService session;
	//Hiển thị toàn bộ đơn hàng
	@GetMapping("")
	public String homeOrder(Model model,@RequestParam("a") Optional<Integer> p) {
		Pageable pageable=PageRequest.of(p.orElse(0),10 );
		Page<Orders> listt=null;
		listt = ordersService.findAll(pageable);
		model.addAttribute("orders", listt);		
		int totalPages = listt.getTotalPages();
		List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
				.boxed()
				.collect(Collectors.toList());
		model.addAttribute("pageNumbers", pageNumbers);
		return "amin_html/order";
		
	}
	//Xác nhận đơn hàng
	@RequestMapping("/confirm/{order-id}")
	public String OrderConfirm(@PathVariable("order-id") Integer id,Model model,Orders order) throws SQLException {		
		Orders o = ordersService.findById(id);
		o.setStatus( 1);	
		ordersService.save(o);							
		return "redirect:/admin/oneshop/order";
		
	} 
	//Xác nhận đơn hàng đã giao đơn hàng
	@RequestMapping("/delivered/{order-id}")
	public String Orderdelivered(@PathVariable("order-id") Integer id,Model model,Orders order) throws SQLException {		
		Orders o = ordersService.findById(id);
		o.setStatus( 2);	
		ordersService.save(o);							
		return "redirect:/admin/oneshop/order";
		
	} 
	//Xác nhận hủy đơn hàng
	@RequestMapping("/cancel/{order-id}")
	public String OrderCancel(@PathVariable("order-id")Integer id,Model model,Orders order) throws SQLException {		
		Orders o = ordersService.findById(id);
		o.setStatus(3);	
		ordersService.save(o);
		
		Products p = null;
		List<OrderDetails> listDe = orderDetailsService.findByOrderId(id);
		for(OrderDetails od : listDe) {
			p = od.getProduct();
			p.setQuantity(p.getQuantity() + od.getQuantity());
			productsrepo.save(p);
		}
		
		return "redirect:/admin/oneshop/order";
		
	} 
	//Chi tiết đơn hàng
	@GetMapping("/detail/{orderid}")
	public String Orderdetail(@PathVariable("orderid") int id,Model model) {
		List<OrderDetails> orderDetails = orderDetailsService.findByOrderId(id);
		model.addAttribute("orderdetail", orderDetails);		
		model.addAttribute("orderdetaiRequest",new OrderDetails());
		return "amin_html/OrderDetails";
		
	} 
	
	 //Tìm kiếm sản phẩm theo tên
		@GetMapping("/searchorder")
		public String search(Model model,@RequestParam("a") Optional<Integer> p,@RequestParam("keywords") Optional<Integer> kw)  {
			
			Pageable pageable=PageRequest.of(p.orElse(0),20);
			Page<Orders> page = ordersService.findByKeyWords(kw.orElse(0), pageable);
			model.addAttribute("orders", page);
			int totalPages = page.getTotalPages();
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
			
			return "amin_html/order";
		}
	
	


	
}
