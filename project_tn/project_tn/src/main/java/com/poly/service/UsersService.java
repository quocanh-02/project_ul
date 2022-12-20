package com.poly.service;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.poly.entity.Authorities;
import com.poly.entity.Users;
import com.poly.service.imbl.UsersNotFoundException;
@Repository
@Transactional
public interface UsersService {

//	List<Users> findAll();
	
	Users doLogin(Users userRequest);
	
	Users save(Users user)  throws SQLException;
	Users get(String resetPassword);
//	
//	void deleteLogical(String username);
	
	Users findByUsername(String username);
	void  updatePassword(Users user ,String newPassword);
	void updateResetPass(String token, String password) throws UsersNotFoundException;

	List<Users> findAll();

	List<Users> getAdministrators();

	Users create(Users user);

	Users update(Users account);






	
//	void update(Users user);
	
	//List<Users> findByKeyword(String keyword);
}
