package com.poly.controller.rest;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.constant.SessionConstant;
import com.poly.dto.CartDto;
import com.poly.entity.Users;
import com.poly.service.CartService;
import com.poly.util.SessionUtil;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/cart")
public class CartApi {
	@Autowired
	CartService service;

	@GetMapping("/update")
	public ResponseEntity<?> doGetUpadate(@RequestParam("productId") Integer productId,
			@RequestParam("quantity") Integer quantity, @RequestParam("isReplace") Boolean isReplace,
			HttpSession session) {
		CartDto currentCart = SessionUtil.getCurrentCart(session);
		service.updateCart(currentCart, productId, quantity, isReplace);
		return ResponseEntity.ok(currentCart);
	}

	@GetMapping("/refresh")
	public ResponseEntity<?> doGetRefresh(HttpSession session) {
		CartDto currentCart = SessionUtil.getCurrentCart(session);
		return ResponseEntity.ok(currentCart);
	}

	@GetMapping("/checkout")
	public ResponseEntity<?> doGetCheckout(@RequestParam("address") String address, @RequestParam("phone") String phone,
			HttpSession session) {
		Users currentUser = SessionUtil.getCurrentUser(session);
		if (ObjectUtils.isEmpty(currentUser)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		CartDto currentCart = SessionUtil.getCurrentCart(session);
		try {
			service.insert(currentCart, currentUser,phone, address);
			session.setAttribute(SessionConstant.CURRENT_CART, new CartDto());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

}
