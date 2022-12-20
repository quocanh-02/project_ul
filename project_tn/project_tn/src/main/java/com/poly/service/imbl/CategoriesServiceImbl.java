package com.poly.service.imbl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.entity.Categories;
import com.poly.repository.CategoriesRepo;
import com.poly.service.CategoriesService;

@Service
public class CategoriesServiceImbl implements CategoriesService {

	@Autowired
	private CategoriesRepo repo;
	
	@Override
	public List<Categories> findAll() {
		return repo.findAll() ;
	}

	@Override
	public Categories create(Categories categories) {
	
		return repo.save(categories);
	}

	@Override
	public Categories update(Categories categories) {
		return repo.save(categories);
	}

	@Override
	public void delete(Integer id) {
		repo.deleteById(id);
		
	}

}
