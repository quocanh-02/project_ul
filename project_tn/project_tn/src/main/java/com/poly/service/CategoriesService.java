package com.poly.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poly.entity.Categories;

@Service
public interface CategoriesService {
	
	List<Categories> findAll();

	Categories create(Categories categories);

	Categories update(Categories categories);

	void delete(Integer id);

	

}
