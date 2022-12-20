package com.poly.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poly.entity.Categories;
import com.poly.service.CategoriesService;

@CrossOrigin("*")
@RestController
@RequestMapping("categories/list")
public class CategoriesRestController {
	@Autowired
	CategoriesService categoriesService ;
	//Hiển thị toàn bộ 
	@GetMapping()
	public List<Categories> indexCategory(Model model) {
		return categoriesService.findAll();
	}
	//Thêm hãng sản phẩm
	@PostMapping
	public ResponseEntity<Categories> create(@Valid  @RequestBody  Categories categories, BindingResult result,
			RedirectAttributes redirectAttributes) {
	if(result.hasErrors()) {
		result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));

	}else {
		Categories save =categoriesService.create(categories);
		
	
	return new ResponseEntity<Categories>(save,HttpStatus.CREATED);
	}
	Categories save =categoriesService.create(categories);
	return new ResponseEntity<Categories>(save,HttpStatus.CREATED);
	}
	//Cập nhật sản phẩm
	@PutMapping("{id}")
	public Categories update(@RequestBody Categories categories) {
		return categoriesService.update(categories);
	}
	//Xóa hãng sản phẩm
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		categoriesService.delete(id);
	}
}
