package com.poly.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.poly.entity.Authorities;

@Service
public interface AuthoritiesService {

	Authorities save(Authorities au);

	List<Authorities> findAll();

	List<Authorities> findAuthoritiesOfAdministrators();

	List<Authorities> getOneByRole(String username);

	Authorities create(Authorities auth);

	void deleteByUsername(String username);






}
