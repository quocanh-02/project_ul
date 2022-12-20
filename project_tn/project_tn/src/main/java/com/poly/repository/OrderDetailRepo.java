package com.poly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.poly.dto.CartDetailDto;
import com.poly.entity.OrderDetails;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetails, Integer>{
   @Modifying(clearAutomatically = true)
	@Query(value = "INSERT INTO order_details(orderId,productId,price,quantity) VALUES(:#{#dto.orderId},:#{#dto.productId},"
    		+ ":#{#dto.price},:#{#dto.quantity})",nativeQuery = true) 
	void insert(@Param("dto")CartDetailDto cartDetailDto);

   
   @Query(value = "select * from order_details where orderid = ?", nativeQuery = true)
   List<OrderDetails> findByOrderId(Integer id);
}
