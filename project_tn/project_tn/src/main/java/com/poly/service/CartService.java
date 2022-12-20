package com.poly.service;

import com.poly.dto.CartDto;
import com.poly.entity.Users;

public interface CartService {
	//Thêm mới,cộng dồn ,thay thế , xóa
	CartDto updateCart(CartDto cart,Integer productId,Integer quantity,Boolean isReplace);
	//Cập nhật tổng số lượng
	Integer getToTalQuantity(CartDto cart);
	//Cập nhật tổng số tiền
	Double getToTalPrice(CartDto cart);
	 void insert(CartDto cartDto,Users user,String phone,String address) throws Exception;
}
