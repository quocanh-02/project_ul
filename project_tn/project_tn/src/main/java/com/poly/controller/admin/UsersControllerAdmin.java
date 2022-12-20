package com.poly.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poly.entity.Products;
import com.poly.entity.Roles;
import com.poly.entity.Users;
import com.poly.service.ProductsService;
import com.poly.service.RolesService;
import com.poly.service.UsersService;



@Controller
public class UsersControllerAdmin {
	@RequestMapping("/admin/oneshop/account")
	public String index() {
		return "amin_html/account";
	}
	
}
