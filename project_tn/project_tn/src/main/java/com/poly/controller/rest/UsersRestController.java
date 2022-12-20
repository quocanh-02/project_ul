package com.poly.controller.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.poly.entity.Users;
import com.poly.repository.UsersRepo;
import com.poly.service.UsersService;

@CrossOrigin("*")
@RestController
@RequestMapping("users/list")
public class UsersRestController {
	@Autowired
	UsersService userService;
	@Autowired
	UsersRepo repo;
	
	@GetMapping()
	public List<Users> getUsers(@RequestParam("admin")Optional <Boolean>admin){
		if(admin.orElse(false)) {
			return userService.getAdministrators();
		}
		return userService.findAll();
	}
	//Thêm tài khoản
	@PostMapping("/create")
	public Users create(@Valid @RequestBody Users user,BindingResult result) {
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
		}
	
			return userService.create(user);
		
		
	}
	@PutMapping("{id}")
	public Users update(@Valid @RequestBody Users account,BindingResult result,@PathVariable("id")String username) {
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
		}
		return userService.update(account);
	}
	
	
	

}
