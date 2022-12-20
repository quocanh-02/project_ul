package com.poly.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.poly.entity.Roles;
import com.poly.service.RolesService;

@CrossOrigin("*")
@RestController
@RequestMapping("/roles/list")
public class RolesRestController {
	@Autowired
	RolesService service;
	@GetMapping
	public List<Roles> index() {
		return service.findAll();
	}

}
