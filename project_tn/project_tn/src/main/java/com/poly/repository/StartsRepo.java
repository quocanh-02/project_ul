package com.poly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.poly.entity.Orders;
@Repository
public interface StartsRepo extends JpaRepository<Orders, Long> {

	@Query(value = "{CALL sp_getTotalPricePerMonth(:month, :year)}", nativeQuery = true)
	String getTotalPricePerMonth(@Param("month") String month,@Param("year")String year);
}
