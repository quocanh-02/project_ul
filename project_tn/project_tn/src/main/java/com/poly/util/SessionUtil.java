package com.poly.util;

import javax.servlet.http.HttpSession;

import com.poly.constant.SessionConstant;
import com.poly.dto.CartDto;
import com.poly.entity.Users;

public class SessionUtil {
	private SessionUtil() {
		
	}
	public static CartDto getCurrentCart(HttpSession session) {
		return (CartDto) session.getAttribute(SessionConstant.CURRENT_CART);
	}
	public static Users getCurrentUser(HttpSession session) {
		return (Users) session.getAttribute(SessionConstant.CURRENT_USER);
	}
}
