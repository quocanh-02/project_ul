package com.poly.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Feedback")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Feedback implements Serializable{
	
	private static final long serialVersionUID = 2599345573093361604L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="id")
	private Integer id;
	@Basic(optional = false)
	@Column(name="content")
	private String content;
	@Column(name = "createdDate")
	@CreationTimestamp
	private Timestamp createdDate;
	//bi-directional many-to-one association to Product
	@ManyToOne(optional = false)
	@JsonIgnore
	@JoinColumn(name="productId")
	private Products product;
	
	@ManyToOne(optional = false)
	@JsonIgnore
	@JoinColumn(name = "username", referencedColumnName = "username")
	@JsonIgnoreProperties(value = { "aplications", "hibernateLazyInitializer" })
	private Users username;


}
