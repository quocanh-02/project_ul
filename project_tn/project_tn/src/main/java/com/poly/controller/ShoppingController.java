package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oneshop")
public class ShoppingController {
//	@GetMapping("/cart")
//	public String index() {
//		return "user_html/cart";
//	}
	@GetMapping("/OrderDetail")
	public String order() {
		return "user_html/order_check";
	}
	@GetMapping("/pay")
	public String pay() {
		return "user_html/pay";
	}
	
}
