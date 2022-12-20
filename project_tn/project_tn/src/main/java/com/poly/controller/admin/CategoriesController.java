package com.poly.controller.admin;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poly.entity.Categories;

@Controller
@RequestMapping("admin/oneshop")
public class CategoriesController {
	
	@GetMapping("categories")
	public String index() {
	
		return "amin_html/Category";
	}


}
