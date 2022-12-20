package com.poly.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping("/admin/oneshop/product")
public class ProductControllerAdmin {

	@GetMapping()
	public String doGetIndex(Model model) {
		return "amin_html/Product";
	}
	
}
