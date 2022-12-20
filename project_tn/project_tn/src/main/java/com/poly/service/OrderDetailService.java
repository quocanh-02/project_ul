package com.poly.service;

import java.util.List;

import com.poly.dto.CartDetailDto;
import com.poly.entity.OrderDetails;

public interface OrderDetailService {
	void inser(CartDetailDto cartDetailDto);

	List<OrderDetails> findByOrderId(Integer id);

	void insert(CartDetailDto cartDetailDto);

}
