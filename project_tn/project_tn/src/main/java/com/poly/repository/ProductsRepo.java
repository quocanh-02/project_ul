package com.poly.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poly.entity.Products;

@Repository
public interface ProductsRepo extends JpaRepository<Products,Integer> {
	//Lấy ra sản phẩm có cùng mã loại
    @Query("SELECT p FROM Products p WHERE   p.category.id=?1 ")
	List<Products> findByIdfindByCategoryId(Integer cid);
    //Lấy ra sản phẩm có cùng mã loại và phân trang
    @Query("SELECT p FROM Products p WHERE p.category.id=?1")
	Page<Products> findByCategoryId(Integer integer, Pageable pageable);
    //Tìm kiếm sản phẩm theo tên
	@Query("SELECT o FROM Products o WHERE o.name LIKE ?1")
	Page<Products> findByKeywords(String keywords, Pageable pageable);

	Products findBySlug(String slug);
	
	Products findByName(String name);
	
	@Query(value="select * from products where isDeleted = 0 and quantity > 0",nativeQuery = true)
	Page<Products> findAllAvailable(Pageable pageable);
	@Query("SELECT p FROM Products p WHERE p.category.description LIKE ?1 and p.isDeleted = 0 and p.quantity > 0 ")
	Page<Products> findByCategoryDescription(String integer, Pageable pageable);
	//Hiển thị 8 sản phẩm mới nhập về
	@Query(value = " select top 6 * FROM Products where quantity > 0 ORDER BY id DESC",nativeQuery = true)
	List<Products> top6Product_new();
	//Cập nhật lại số lượng
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE products SET quantity=?1 WHERE id=?2",nativeQuery = true)
	void updateQuantity(Integer newquantity,Integer id);
	//Hiển thị các sản phẩm giảm giá
	@Query(value = "SELECT * FROM products WHERE discount > 0",nativeQuery = true)
	Page<Products> findAllDiscount(Pageable pageable);
	//Hiển thị 6 sản phẩm giảm giá theo chiều giảm dần
    @Query(value = "SELECT TOP 6 * FROM products WHERE discount > 0 ORDER BY id DESC",nativeQuery = true)
	List<Products> findByDiscountTop6();
    
    @Query(value="Select c.description, ISNULL(sum(odt.Quantity),0) from Categories c  "
			+ "inner join Products p on c.Id = p.CategoryId "
			+ "inner join order_details odt on p.Id = odt.ProductId "
			+ "inner join Orders o on odt.OrderId = o.Id "
			+ "Where cast(o.CreatedDate as date) >= DateAdd(day,-30,cast(getdate() as date)) "
			+ "group by c.description",nativeQuery = true)
	List<Object[]> numberOfProductSoldByType();


	
}
