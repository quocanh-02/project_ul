package com.poly.repository;

import org.springframework.data.repository.CrudRepository;

import com.poly.entity.*;

public interface PayRepo extends CrudRepository<Payment,Integer>{
	Payment findByPaypalOrderId(String paypalOrderId);
}
