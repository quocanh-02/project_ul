package com.poly.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.service.StartService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private StartService startService;

	@GetMapping("")
	public String dogetIndex(Model model) {
		String chartData[][] = startService.getTotalPriceLast6Months();
		model.addAttribute("chartData",chartData);
		return "amin_html/statistical";
	}
	
	
}
