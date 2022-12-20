package com.poly.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8415665757292053130L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "address")
	private String address;
	
	

	@Column(name = "phone")
	private String phone;

	@Column(name = "createdDate")
	@CreationTimestamp
	private Timestamp createdDate;
	
	@Column(name = "status")
	private Integer status;

	@ManyToOne
	@JoinColumn(name = "username", referencedColumnName = "username")
	@JsonIgnoreProperties(value = { "aplications", "hibernateLazyInitializer" })
	private Users user;

	// bi-directional many-to-one association to Payment
	@ManyToOne
	@JoinColumn(name = "payId", referencedColumnName = "id")
	@JsonIgnoreProperties(value = { "aplications", "hibernateLazyInitializer" })
	private Payment payment;
}
