package com.poly.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class Users implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3135122392967624722L;

	@Id
	@Column(name = "username")
	@NotNull

	private String username;

	@Column(name = "fullname")
	@NotNull
	@Size(min =5,max =255)
	private String fullname;

	@Column(name = "password")
	@NotNull
	@Size(min = 6,max =255)
	private String password;

	@Column(name = "email")
	private String email;

	

	@Column(name = "imgUrl")
	private String imgUrl;

	@Column(name = "isDeleted")
	private Boolean isDeleted;

	  @Column(name = "reset_password_token")
	    private String resetPasswordToken;
	  


	  @JsonIgnore
		@OneToMany(mappedBy="users",fetch = FetchType.EAGER)
		private List<Authorities> authorities;

	
	


}
