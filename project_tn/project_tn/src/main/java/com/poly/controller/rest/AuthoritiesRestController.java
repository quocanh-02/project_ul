package com.poly.controller.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.entity.Authorities;
import com.poly.service.AuthoritiesService;

@CrossOrigin("*")
@RestController
@RequestMapping("authorities")
public class AuthoritiesRestController {
	@Autowired
	AuthoritiesService service;
	@GetMapping("list")
	public List<Authorities> findAll(@RequestParam("admin")Optional <Boolean> admin){
		if(admin.orElse(false)) {
			return service.findAuthoritiesOfAdministrators();
		}
		return service.findAll();
	}
	
	@GetMapping("authoritiesOne")
	public List<Authorities> getOneByRole(@RequestParam("username")String username){
		return service.getOneByRole(username);
	}
	
	@PostMapping("list")
	public Authorities post(@RequestBody Authorities auth) {
		return service.create(auth);
	}
	
	@DeleteMapping("authoritiesOne/{username}")
	public void deleteByUsername(@PathVariable("username") String username) {
		System.out.println("username del: "+username);
		service.deleteByUsername(username);
	}

}
