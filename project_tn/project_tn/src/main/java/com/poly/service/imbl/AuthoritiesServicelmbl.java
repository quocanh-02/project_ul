package com.poly.service.imbl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.entity.Authorities;
import com.poly.entity.Users;
import com.poly.repository.AuthoritiesRepo;
import com.poly.repository.UsersRepo;
import com.poly.service.AuthoritiesService;


@Service
public class AuthoritiesServicelmbl implements AuthoritiesService{
    @Autowired
    AuthoritiesRepo repo;
    @Autowired
    UsersRepo userpepo;
	@Override
	public Authorities save(Authorities au)  {
		return repo.save(au);
	}
	@Override
	public List<Authorities> findAll() {
		return repo.findAll();
	}

	@Override
	public List<Authorities> findAuthoritiesOfAdministrators() {
		List<Users> list=userpepo.getAdministrators();
		return repo.authoritiesOf(list);
	}
	@Override
	public List<Authorities> getOneByRole(String username) {
		return repo.getOneByRole(username);
	}
	@Override
	public Authorities create(Authorities auth) {
		// TODO Auto-generated method stub
		return repo.save(auth);
	}
	@Override
	public void deleteByUsername(String username) {
		repo.deleteById(username);
		
	}

	
	


}
