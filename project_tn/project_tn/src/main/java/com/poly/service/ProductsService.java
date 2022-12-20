package com.poly.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.poly.entity.Products;


public interface ProductsService {
//	List<Products> findAll();


	Products findById(Integer  id);



	List<Products> findByCategoryId(Integer id);

	Page<Products> findAll(Pageable pageable);

	Page<Products> findByCategoryId(Integer integer, Pageable pageable);


	Page<Products> findByKeyWords(String string, Pageable pageable);


	Products findBySlug(String slug);


	Page<Products> findByCategoryDescription(String integer, Pageable pageable);

	List<Products> top6Product_new();
	void updateQuantity(Integer newquantity,Integer id);



	List<Products> findAll();



	Products create(Products pro);



	Products update(Products pro);



	Page<Products> findAllDiscount(Pageable pageable);



	List<Products> findByDiscountTop6();



	List<Object[]> numberOfProductSoldByType();












}
