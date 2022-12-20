package com.poly.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.Config.PaymentConfig;
import com.poly.constant.RolesConstant;
import com.poly.entity.Authorities;
import com.poly.entity.Roles;
import com.poly.entity.Users;
import com.poly.service.AuthoritiesService;
import com.poly.service.RolesService;
import com.poly.service.UsersService;
import com.poly.util.SessionUtil;

@Controller
@RequestMapping("/oneshop")
public class HomeController {

	@Autowired
	private UsersService usersService;

	@Autowired
	private RolesService rolesService;

	@Autowired
	private AuthoritiesService auService;
	

	@GetMapping(value = { "/", "" })
	public String doGetRedirectIndex() {
		return "redirect:/oneshop/index";
	}

	// Đăng nhập
	@GetMapping("/login")
	public String doGetLogin(Model model) {
		model.addAttribute("userRequest", new Users());
		return "user_html/login";
	}

	@PostMapping("/login")
	public String doPostLogin(@ModelAttribute("userRequest") Users userRequest, HttpSession session) {
		Users userResponse = usersService.doLogin(userRequest);
		Roles roles = rolesService.findByName(RolesConstant.USER);
		Authorities au = new Authorities(0, roles,userResponse);
	
	if (ObjectUtils.isNotEmpty(userResponse)) {
		session.setAttribute("currentUser", userResponse);
		return "redirect:/oneshop/home";
	}

		
		
	

		return "redirect:/oneshop/login";
	}

	// Đăng xuất
	@GetMapping("/logout")
	public String goGetLogout(HttpSession session) {
		session.removeAttribute("currentUser");
		return "redirect:/oneshop/index";
	}

	// Đăng ký
	@GetMapping("/register")
	public String doGetRegister(Model model) {
		model.addAttribute("userRequest", new Users());
		return "user_html/register";
	}

	@PostMapping("/register")
	public String doPostRegister(@ModelAttribute("userRequest") Users userRequest, HttpSession session)
			throws SQLException {
		Users userResponse = usersService.save(userRequest);
		Roles roles = rolesService.findByName(RolesConstant.USER);
		Authorities au = new Authorities(0, roles,userResponse);
		auService.save(au);
		if (ObjectUtils.isNotEmpty(userResponse)) {
			session.setAttribute("currentUser", userResponse);
			return "redirect:/oneshop/index";
		} else {
			return "redirect:/oneshop/register";
		}
	}

	// Cập nhật tài khoản
			@GetMapping("/user")
			public String user(Model model,HttpSession session) {
				Users c = SessionUtil.getCurrentUser(session);
				usersService.findByUsername(c.getUsername());
				model.addAttribute("user",c);
				return "user_html/account";
			}
			@PostMapping("/user")
			public String doPostUser(@ModelAttribute("user") Users userRequest,HttpSession session) throws SQLException {
				Users c = SessionUtil.getCurrentUser(session);
				usersService.findByUsername(c.getUsername());
				c.setFullname(userRequest.getFullname());
				c.setEmail(userRequest.getEmail());
				usersService.save(c);	
				return "user_html/account";
			}
			
			
			


}
