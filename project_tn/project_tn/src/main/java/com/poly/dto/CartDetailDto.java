package com.poly.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID =-460622231132801639L;
	private Integer orderId;
	private Integer productId;
	private String name;
	//giá tiền ở thời điểm bán
	private Double price;
	
	//giá tiền ở thời điểm bán
	private Integer discount;
	//Số lượng bn
	private Integer quantity;
	private String recipientName;
	//Hình ảnh sp
	private String imgUrl;
	private String slug;
}
