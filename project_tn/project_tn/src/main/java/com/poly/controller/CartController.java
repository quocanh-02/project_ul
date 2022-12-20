package com.poly.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dto.CartDto;
import com.poly.service.CartService;
import com.poly.util.SessionUtil;

@Controller
@RequestMapping("/oneshop/cart")
public class CartController {
	@Autowired
	CartService service;
	@GetMapping("")
	public String index(Model model) {
		return "user_html/cart";
	}

	@GetMapping("/update")
	public String doGetUpadate(@RequestParam("productId")Integer productId,
			@RequestParam("quantity") Integer quantity,
			@RequestParam("isReplace")Boolean isReplace
			,HttpSession session){
		CartDto currentCart=SessionUtil.getCurrentCart(session);
		service.updateCart(currentCart, productId, quantity, isReplace);
		return "user_html/cart::#align-middle";
	}
}
