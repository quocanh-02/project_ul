package com.poly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.entity.Roles;

@Repository
public interface RolesRepo extends JpaRepository<Roles, Long> {

	

	Roles findByName(String name);
}
