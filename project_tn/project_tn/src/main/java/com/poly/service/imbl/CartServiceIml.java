package com.poly.service.imbl;

import java.util.HashMap;

import javax.transaction.Transactional;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dto.CartDetailDto;
import com.poly.dto.CartDto;
import com.poly.entity.Orders;
import com.poly.entity.Products;
import com.poly.entity.Users;
import com.poly.service.CartService;
import com.poly.service.OrderDetailService;
import com.poly.service.OrderService;
import com.poly.service.ProductsService;

@Service
public class CartServiceIml implements CartService{
   @Autowired
   private ProductsService service;
   @Autowired
   private OrderService orderService;
   @Autowired
   private OrderDetailService orderDetailService;

	@Autowired
   private ProductsService productsService;
	@Override
	public CartDto updateCart(CartDto cart, Integer productId, Integer quantity, Boolean isReplace) {
        Products product=service.findById(productId);
        HashMap<Integer, CartDetailDto> details= cart.getDetail();
        
		//1.Thêm mới	
		if(!details.containsKey(productId)) {
			CartDetailDto dto=createNewCartDetail(product, quantity);
			details.put(productId, dto);
		}else if(quantity>0){
			//2.Cập nhật
			if(isReplace) {
				//2.2 Thay thế hoàn toàn(isReplace=true)
				//Cách 1
//				CartDetailDto productNeedToUpdate=details.get(productId);
//				productNeedToUpdate.setQuantity(quantity);
//				details.put(productId, productNeedToUpdate);
				//Cách 2
				details.get(productId).setQuantity(quantity);
			}else {
				//2.1 Cộng dồn
				Integer currentQuantity=details.get(productId).getQuantity();
				Integer newQuantity=currentQuantity +quantity;
				details.get(productId).setQuantity(newQuantity);
			}
		}else {
			//3 Xóa(cập nhật với quantity =0)
			details.remove(productId);
		}
		//Cập nhật số lượng
		cart.setTotalQuantily(getToTalQuantity(cart));
		//Cập nhật tổng tiền
		cart.setTotalPrice(getToTalPrice(cart));
		return cart;
	}
	//Tổng số lượng sản phẩm có
	@Override
	public Integer getToTalQuantity(CartDto cart) {
	   Integer totalQuantity=0;
	   HashMap<Integer, CartDetailDto> details= cart.getDetail();
	   for(CartDetailDto cartDetail : details.values()) {
		   totalQuantity += cartDetail.getQuantity();
	   }
	   return totalQuantity;
	}
	@Override 
	public Double getToTalPrice(CartDto cart) {
		 Double totalPrice=0D;
		   HashMap<Integer, CartDetailDto> details= cart.getDetail();
		   for(CartDetailDto cartDetail : details.values()) {
			   totalPrice += (cartDetail.getPrice()*cartDetail.getQuantity());
		   }
		   return totalPrice;
	}
	//Tạo hàm thêm mới
	private CartDetailDto createNewCartDetail(Products product,Integer quantity) {
		CartDetailDto dto=new CartDetailDto();
		dto.setProductId(product.getId());
		dto.setQuantity(quantity);
		dto.setPrice(product.getPrice());
		dto.setImgUrl(product.getImgUrl());
		dto.setSlug(product.getSlug());
		dto.setName(product.getName());
		return dto;
	}
	@Transactional(rollbackOn = {Exception.class})
	@Override
	public void insert(CartDto cartDto, Users user, String phone, String address) throws Exception {
		
		if(StringUtils.isAnyEmpty(address,phone)) {
    		throw new Exception("Address or phone must be not null or empty or whitespace ");
    	}   
    	
    	
		
		// insert vao bang order
		Orders order = new Orders();
		order.setUser(user);
		
		order.setPhone(phone);
		order.setAddress(address);
		order.setStatus(0);
		
		Orders orderResponse = orderService.insert(order);
		
		if(ObjectUtils.isEmpty(orderResponse)) {
			throw new Exception("Insert into order table false");
		}
		
		if(cartDto.getTotalQuantily()==0) {
			throw new Exception("Insert into order table false");
		}
		
for(CartDetailDto cartDetailDto : cartDto.getDetail().values()) {
			
			Products product = productsService.findById(cartDetailDto.getProductId());
			
			if(cartDetailDto.getQuantity()==0) {
				throw new Exception("Insert into order table false");
			}
			if(checkQuantity(product, cartDetailDto)) {
				cartDetailDto.setOrderId(orderResponse.getId());
				orderDetailService.insert(cartDetailDto);			
				//update new quantity cho bang product			
				Integer newQuantity = product.getQuantity() - cartDetailDto.getQuantity();
				productsService.updateQuantity(newQuantity, product.getId());
			}else {
				throw new Exception("Order quantity must be less than curent product quạntity");
			}
			
			
		}
	}
	
	private boolean checkQuantity(Products product, CartDetailDto cartDetail) {
    	return  product.getQuantity()>= cartDetail.getQuantity();
    }




}
