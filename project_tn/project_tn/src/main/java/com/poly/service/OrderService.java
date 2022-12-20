package com.poly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.poly.entity.Orders;
import com.poly.entity.Products;

public interface OrderService {
	Orders insert(Orders order);

	

	Orders findById(Integer id);

	Orders save(Orders o);

	Page<Orders> findByusername(String username, int status, PageRequest of);

	

	Page<Orders> findAll(Pageable pageable);



	Page<Orders> findByKeyWords(Integer p, Pageable pageable);



	List<Object[]> getRevenueLast7Days();




	
	
	
	
	

}
