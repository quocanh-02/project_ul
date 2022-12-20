package com.poly.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="order_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5503782083975441487L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="price")
	private Double price;
	
	@Column(name="quantity")
	private Integer quantity;
	@ManyToOne
	@JoinColumn(name="productId",referencedColumnName = "id")
	@JsonIgnoreProperties(value= {"aplications","hibernateLazyInitializer"})
	private Products product;
	
	@ManyToOne
	@JoinColumn(name="orderId")
	@JsonIgnoreProperties(value= {"aplications","hibernateLazyInitializer"})
	private Orders order;
	

}
