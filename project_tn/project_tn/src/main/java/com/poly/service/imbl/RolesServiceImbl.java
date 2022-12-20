package com.poly.service.imbl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.constant.RolesConstant;
import com.poly.entity.Roles;
import com.poly.repository.RolesRepo;
import com.poly.service.RolesService;

@Service
public class RolesServiceImbl implements RolesService {

	@Autowired
	private RolesRepo repo;

//	@Override
//	public Roles findByDescription(String description) {
//		// TODO Auto-generated method stub
//		return repo.findByDescription(description);
//	}

	@Override
	public Roles findByName(String name) {
		// TODO Auto-generated method stub
		return repo.findByName(name);
	}

	@Override
	public List<Roles> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	

}
