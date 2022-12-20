package com.poly.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.service.OrderService;
import com.poly.service.ProductsService;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin/oneshop/statistical")
public class Statistical{
	@Autowired
	OrderService orderService;
	@Autowired
	ProductsService productService;
	
	@GetMapping("secondRow")
	public Map<String,List<Object[]>> showSecond(){
		Map<String,List<Object[]>> map = new HashMap<>();
		map.put("revenueLast7Days", orderService.getRevenueLast7Days());
		map.put("productSoldByCate", productService.numberOfProductSoldByType());
		return map;
	}

}
