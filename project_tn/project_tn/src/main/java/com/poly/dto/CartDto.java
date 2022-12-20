package com.poly.dto;

import java.io.Serializable;
import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//Chứa các thông tin khi thanh toán(người mua,địa chỉ,sđt,ds sp mua,tổng tiền hóa đơn)
public class CartDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID =-460622231132801639L;
	private Integer orderId;
	private String address;
	private String phone;
	//Tổng tiền để mặc định đơn hàng mới là 0đ
	private Double totalPrice=0D;
	//Tổng số lượng để mặc định là 0 sản phẩm
	private  Integer totalQuantily=0;
	//Danh sách hóa đơn chi tiết
	private HashMap<Integer, CartDetailDto> detail= new HashMap<>();
	

}
