package com.poly.service.imbl;

import java.sql.SQLException;
import java.util.List;


import org.apache.commons.lang3.ObjectUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poly.entity.Users;
import com.poly.repository.UsersRepo;
import com.poly.service.UsersService;

@Service
@Transactional
public class UsersServiceImbl implements UsersService {

	private final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	
	@Autowired
	private UsersRepo repo;

//	@Autowired
//	private RolesService rolesService;
	
	@Override
	public Users doLogin(Users userRequest) {
		Users userResponse = repo.findByusername(userRequest.getUsername());
		
		if(ObjectUtils.isNotEmpty(userResponse)) {
			boolean checkPassword = bcrypt.matches(userRequest.getPassword(), userResponse.getPassword());
			return checkPassword ? userResponse :null;
		}
		return null;
	}
	

	@Override	
	@Transactional(rollbackFor = {Throwable.class})
	public Users save(Users user) throws SQLException {
		
		user.setIsDeleted(Boolean.FALSE);
		String password = bcrypt.encode(user.getPassword());
		user.setPassword(password);
		return repo.saveAndFlush(user);
	}
	
	
//	@Override
//	public void updateResetPass(String password,String email)  {
//		Users user=repo.findByEmail(email);
//		if(user !=null) {
//			user.setReset_password_token(password);
//			repo.save(user);
//		}else {
//			throw new UsersNotFoundException("Could not find"+ email);
//		}
//	}
	@Override
	public Users get(String resetPassword) {
		return repo.findByResetPasswordToken(resetPassword);
	}
	
	
	@Override
	public void updatePassword(Users user ,String newPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodePassword=passwordEncoder.encode(newPassword);
		user.setPassword(encodePassword);
		user.setResetPasswordToken(null);
		repo.save(user);
	}
	
	
//	@Override
//	public List<Users> findAll() {
//		// TODO Auto-generated method stub
//		return repo.findByIsDeleted(Boolean.FALSE) ;
//	}
//
//	@Override
//	@Transactional(rollbackOn = {Exception.class,Throwable.class})
//	public void deleteLogical(String username) {
//		repo.deleteLogical(username);
//		
//	}

	@Override
	public Users findByUsername(String username) {
		return repo.findByusername(username);
	}


	@Override
	public void updateResetPass(String token, String email) throws UsersNotFoundException {
		 Users customer = repo.findByEmail(email);
	        if (customer != null) {
	            customer.setResetPasswordToken(token);
	            repo.save(customer);
	        } else {
	            throw new UsersNotFoundException("Could not find any customer with the email " + email);
	        }
		
	}


	@Override
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}


	@Override
	public List<Users> getAdministrators() {
		return repo.getAdministrators();
	}



	
	@Override
	@Transactional(rollbackFor = {Throwable.class})
	public Users create(Users user) {
		user.setIsDeleted(Boolean.FALSE);
		String password = bcrypt.encode(user.getPassword());
		user.setPassword(password);
		return repo.saveAndFlush(user);
	}


	@Override
	public Users update(Users account) {
		account.setIsDeleted(Boolean.FALSE);
		String password = bcrypt.encode(account.getPassword());
		account.setPassword(password);
		return repo.save(account);
	}




//	@Override
//	public void update(Users user) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public List<Users> findByKeyword(String keyword) {
//		// TODO Auto-generated method stub
//		return null;
//	}


}
