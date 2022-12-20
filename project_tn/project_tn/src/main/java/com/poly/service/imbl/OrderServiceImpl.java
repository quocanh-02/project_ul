package com.poly.service.imbl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.entity.Orders;
import com.poly.entity.Products;
import com.poly.repository.OrdersRepo;
import com.poly.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrdersRepo repo;
    
    @Transactional(value = TxType.REQUIRED )
	@Override
	public Orders insert(Orders order) {
		return repo.saveAndFlush(order);
	}

	
	@Override
	public Orders findById(Integer id) {
		return repo.findById(id).get();
	}

	@Override
	public Orders save(Orders o) {
		return repo.saveAndFlush(o);
	}

	@Override
	public Page<Orders> findByusername(String username, int status, PageRequest of) {
		return repo.findByusername(username,status,of);
	}

	

	@Override
	public Page<Orders> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}


	

	@Override
	public Page<Orders> findByKeyWords(Integer p, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findByKeyWords(p,pageable);
	}


	@Override
	public List<Object[]> getRevenueLast7Days() {
		return repo.getRevenueLast7Days();
	}

}
