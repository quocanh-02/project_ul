package com.poly.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poly.entity.Products;
import com.poly.service.ProductsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/product/list")
public class ProductRestController {
	@Autowired
	ProductsService service;
	@GetMapping
	public 	List<Products> index() {
		return service.findAll();
	}
	@PostMapping
	public Products create (@Valid  @RequestBody Products pro, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));

		}
		return service.create(pro);
	}
	@PutMapping("{id}")
	public Products update( @Valid  @RequestBody Products pro,BindingResult result) {
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));

		}
		return service.update(pro);
	}
}
