package com.poly.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poly.entity.Roles;
import com.poly.entity.Users;

@Service
public interface RolesService {

	

	Roles findByName(String name);

	List<Roles> findAll();
}
