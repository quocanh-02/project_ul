package com.poly.controller;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.entity.Products;
import com.poly.repository.ProductsRepo;
import com.poly.service.FeedbackService;
import com.poly.service.ProductsService;
import com.poly.service.SessionService;

@Controller
@RequestMapping("/oneshop")
public class ProductController {
	@Autowired
	ProductsService service;
	@Autowired
	SessionService session;
	//Hiển thị toàn bộ sản phẩm - phân trang - danh mục
	@GetMapping("/index")
	public String index(Model model,@RequestParam("a") Optional<Integer> p,@RequestParam("sort" )Optional<String>cid,@RequestParam("name") Optional<String> name){
		Pageable pageable=PageRequest.of(p.orElse(0),6 );
		Page<Products> listt=null;
		if(cid.isPresent()) {
			listt=service.findByCategoryDescription(cid.get(),pageable);
		}else {
			listt= service.findAll(pageable);
		}
		model.addAttribute("items", listt);
		int totalPages = listt.getTotalPages();
		List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
				.boxed()
				.collect(Collectors.toList());
		model.addAttribute("pageNumbers", pageNumbers);
		return "user_html/product";
	}
	//Hiển thị chi tiết sản phẩm
	@GetMapping("/detail/{slug}")
	public String detail(Model model,@PathVariable("slug")String slug) {
		Products item=service.findBySlug(slug);
		model.addAttribute("items", item);
		//Bình luận 
		
		return "user_html/detail";
	}
    //Tìm kiếm sản phẩm theo tên
	@GetMapping("/search")
	public String search(Model model,@RequestParam("a") Optional<Integer> p,@RequestParam("keywords") Optional<String> kw) {
		String keywords = kw.orElse(session.get("keywords", ""));
		session.set("keywords", keywords);
		Pageable pageable=PageRequest.of(p.orElse(0),6 );
		Page<Products> page = service.findByKeyWords("%"+keywords+"%", pageable);
		model.addAttribute("items", page);
		int totalPages = page.getTotalPages();
		List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
				.boxed()
				.collect(Collectors.toList());
		model.addAttribute("pageNumbers", pageNumbers);
		
		return "user_html/product";
	}
	//Hiển thị 6 sản phẩm mới nhập về
	@GetMapping("/home")
	public String home(Model model) {
		List<Products> list=service.top6Product_new();
		model.addAttribute("item", list);
		List<Products> listt=service.findByDiscountTop6();
		model.addAttribute("items", listt);
		return "user_html/index";
	}
	//Hiển thị sản phẩm giảm giá
	@GetMapping("/discount")
	public String discount(Model model,@RequestParam("a") Optional<Integer> p,@RequestParam("sort" )Optional<String>cid,@RequestParam("name") Optional<String> name){
		Pageable pageable=PageRequest.of(p.orElse(0),6 );
		Page<Products> list= service.findAllDiscount(pageable);
		model.addAttribute("items", list);
		int totalPages = list.getTotalPages();
		List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
				.boxed()
				.collect(Collectors.toList());
		model.addAttribute("pageNumbers", pageNumbers);
		return "user_html/discount";
	}
	
	

}
