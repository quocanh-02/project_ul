package com.poly.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.poly.entity.Users;


@Repository
@Transactional
public interface UsersRepo extends JpaRepository<Users, String> {

	//Select * from users where username=?
//		Users findByemail(String email);
//		
//		List<Users> findByIsDeleted(Boolean isDeleted);
//		 
//		//xóa user
//		@Modifying(clearAutomatically = true)
//		@Query(value = "UPDATE users SET isDeleted = 1 WHERE username = ?",nativeQuery = true)
//		void deleteLogical(String username);
//
//		
//		//update có pass		
//		@Modifying(clearAutomatically = true)
//		@Query(value = "UPDATE users SET email=?1,hashPassword = ?2,fullname = ?3 WHERE username=?4",nativeQuery = true)
//		void update(String email,String hashPassword,String fullname,String username);
//
//		
//		//update ko pass
//		@Modifying(clearAutomatically = true)
//		@Query(value = "UPDATE users SET email=?1,fullname = ?2 WHERE username=?3",nativeQuery = true)
//		void updateNonPass(String email,String fullname,String username);
//		
//		//tìm kiếm
//		@Modifying(clearAutomatically = true)
//		@Query(value="SELECT * FROM users WHERE username LIKE ?1 or fullname LIKE ?1  ",nativeQuery = true)
//		List<Users> findByKeyword(String keyword);
//
		Users findByusername(String username);
		
		@Query("SELECT c FROM Users c WHERE c.email =?1")
		Users findByEmail(String Email);
		
		//Tìm kiếm thông tin theo email
		public  Users findByResetPasswordToken(String token);

		@Query("Select Distinct ar.users From Authorities ar where ar.roles.id IN (1,2)")
		List<Users> getAdministrators();
		
		//@Query(" select * from users u inner join authorities a on u.username=a.username where a.roleId=1 and username Like ?1")
	
		
}

