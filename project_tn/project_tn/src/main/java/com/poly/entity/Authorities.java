



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
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Entity
@Getter
@Setter
@Table(name="authorities")
@AllArgsConstructor
@NoArgsConstructor
public class Authorities implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7880228631885238813L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name= "roleId", referencedColumnName = "id")
	@JsonIgnoreProperties(value= {"aplications","hibernateLazyInitializer"})
	private Roles roles;	
	
	@ManyToOne
	@JoinColumn(name= "username", referencedColumnName = "username")
	@JsonIgnoreProperties(value= {"aplications","hibernateLazyInitializer"})
	private Users users;



	

	

}


