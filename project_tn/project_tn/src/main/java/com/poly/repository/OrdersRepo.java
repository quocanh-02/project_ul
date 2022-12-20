package com.poly.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poly.entity.Orders;
import com.poly.entity.Products;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Integer>{
	@Query(value = "select * from orders where username = ?1 and status = ?2", nativeQuery = true)
	Page<Orders> findByusername(String username, int status, PageRequest of);
	@Query(value="SELECT * FROM orders  WHERE id = ?1", nativeQuery = true)
	Page<Orders> findByKeyWords(Integer p, Pageable pageable);
	
	@Query(value ="	Select t.last7Days as 'date', ISNULL(sum(price*quantity),0) as ' totalPayment' \r\n"
			+ "			From (Select cast(Getdate()as Date) last7Days  \r\n"
			+ "				Union all \r\n"
			+ "		Select DateAdd(day,-1,cast(getdate()as date)) \r\n"
			+ "				Union all \r\n"
			+ "			Select DateAdd(day,-2,cast(getdate()as date)) \r\n"
			+ "				Union all \r\n"
			+ "			Select DateAdd(day,-3,cast(getdate()as date)) \r\n"
			+ "				Union all \r\n"
			+ "				Select DateAdd(day,-4,cast(getdate()as date)) \r\n"
			+ "				Union all \r\n"
			+ "			Select DateAdd(day,-5,cast(getdate()as date)) \r\n"
			+ "				Union all \r\n"
			+ "			Select DateAdd(day,-6,cast(getdate()as date)) \r\n"
			+ "				Union all \r\n"
			+ "		Select DateAdd(day,-7,cast(getdate()as date)) ) t Left Join Orders t1 on cast(t.last7Days as date) = Cast(t1.CreatedDate as date) \r\n"
			+ "			left join order_details dt on  t1.Id = dt.OrderId \r\n"
			+ "			Group by cast(t.last7Days as Date);", nativeQuery = true)
	List<Object[]> getRevenueLast7Days();
	
	

}
