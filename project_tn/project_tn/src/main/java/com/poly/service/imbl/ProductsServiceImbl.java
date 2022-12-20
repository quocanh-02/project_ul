package com.poly.service.imbl;

import java.util.List;



import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.poly.entity.Products;
import com.poly.repository.ProductsRepo;
import com.poly.service.ProductsService;


@Repository
@Transactional
public class ProductsServiceImbl implements ProductsService {
	@Autowired
	ProductsRepo repo;
	
	@Override
	public List<Products> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll() ;
	}
	


	@Override
	public Products findById(Integer id) {
		return repo.findById(id).get();
	}

	@Override
	public List<Products> findByCategoryId(Integer cid) {
		return repo.findByIdfindByCategoryId(cid);
	}

	@Override
	public Page<Products> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAllAvailable(pageable);
	}

	@Override
	public Page<Products> findByCategoryId(Integer integer, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findByCategoryId(integer,pageable);
	}

	@Override
	public Page<Products> findByKeyWords(String string, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findByKeywords(string, pageable);
	}



	@Override
	public Products findBySlug(String slug) {
		// TODO Auto-generated method stub
		return repo.findBySlug(slug);
	}



	@Override
	public Page<Products> findByCategoryDescription(String integer, Pageable pageable) {
		return  repo.findByCategoryDescription(integer,pageable);
	}



	@Override
	public List<Products> top6Product_new() {
		return repo.top6Product_new();
	}



	
	
	
	@Override
	public void updateQuantity(Integer newquantity, Integer id) {
		repo.updateQuantity(newquantity, id);
	}



	@Override
	public Products create(Products pro) {
		return repo.save(pro);
	}



	@Override
	public Products update(Products pro) {
		// TODO Auto-generated method stub
		return repo.save(pro);
	}



	@Override
	public Page<Products> findAllDiscount(Pageable pageable) {
		return repo.findAllDiscount(pageable);
	}



	@Override
	public List<Products> findByDiscountTop6() {
		// TODO Auto-generated method stub
		return repo.findByDiscountTop6();
	}



	@Override
	public List<Object[]> numberOfProductSoldByType() {
		return repo.numberOfProductSoldByType();
	}



}
