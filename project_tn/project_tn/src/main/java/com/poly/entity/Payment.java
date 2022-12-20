package com.poly.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment implements Serializable{
	
	private static final long serialVersionUID = 5503782083975441487L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="paypalOrderId")
	private String paypalOrderId;
	@Column(name="paypalOrderStatus")
	private String paypalOrderStatus;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="payment")
	@JsonIgnoreProperties(value= {"aplications","hibernateLazyInitializer"})
	private List<Orders> orders;

}
