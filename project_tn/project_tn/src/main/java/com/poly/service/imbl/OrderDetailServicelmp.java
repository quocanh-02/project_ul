package com.poly.service.imbl;


import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dto.CartDetailDto;
import com.poly.entity.OrderDetails;
import com.poly.repository.OrderDetailRepo;
import com.poly.service.OrderDetailService;


@Service
public class OrderDetailServicelmp implements OrderDetailService{
    @Autowired
    OrderDetailRepo repo;
    @Transactional(value = TxType.REQUIRED )
	@Override
	public void inser(CartDetailDto cartDetailDto) {
		repo.insert(cartDetailDto);
		
	}
	@Override
	public List<OrderDetails> findByOrderId(Integer id) {
		return repo.findByOrderId(id);
	}
	@Override
	public void insert(CartDetailDto cartDetailDto) {
		repo.insert(cartDetailDto);
		
	}

}
