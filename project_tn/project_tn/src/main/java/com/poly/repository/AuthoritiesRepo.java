package com.poly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.poly.entity.Authorities;
import com.poly.entity.Users;


public interface AuthoritiesRepo  extends JpaRepository<Authorities,Integer> {
	@Query("Select Distinct a From Authorities a where a.users IN ?1")
	List<Authorities> authoritiesOf(List<Users> users);
	@Query("Select a From  Authorities a where a.users.username like ?1")
	List<Authorities> getOneByRole(String username);
	@Transactional
	@Modifying
	@Query("Delete from Authorities where username = ?1")
	void deleteById(String username);

}
