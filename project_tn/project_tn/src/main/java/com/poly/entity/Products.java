package com.poly.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Products implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7821333210049293058L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	@NotNull

	private String name;

	@Column(name = "quantity")
	@NotNull
	@Min(0)
	private Integer quantity;

	@Column(name = "price")
	@NotNull
	@Min(1)
	private Double price;

	@Column(name = "imgUrl")
	private String imgUrl;

	@Column(name = "description")
	@NotNull
	@Size(min = 10,max = 1000)
	private String description;

	@Column(name = "slug")
	@NotNull
	@Size(min = 1,max = 255)
	private String slug;

	@Column(name = "isDeleted")
	private boolean isDeleted;
	
	@Column(name = "discount")
	@NotNull
	@Min(0)
	private Integer discount;
	
	@Column(name = "feature")
	@NotNull
	//@Size(min = 2,max = 255)
	private String feature;
	//
	@Column(name = "screenTechnology")
	@NotNull
	//@Size(min = 2,max = 255)
	private String screenTechnology;

	@Column(name = "resolution")
	@NotNull
	//@Size(min = 2,max = 255)
	private String resolution;

	@Column(name = "widescreen")
	@NotNull
	//@Size(min = 2,max = 255)
	private String widescreen;

	@Column(name = "maximumBrightness")
	@NotNull
	//@Size(min = 2,max = 255)
	private String maximumBrightness;

	@Column(name = "touchGlass")
	@NotNull
	//@Size(min = 2,max = 255)
	private String touchGlass;

	@Column(name = "film ")
	@NotNull
	//@Size(min = 2,max = 255)
	private String film ;

	@Column(name = "flashLight")
	@NotNull
	//@Size(min = 2,max = 255)
	private String flashLight;
	
	@Column(name = "frontCameraResolution")
	@NotNull
	//@Size(min = 2,max = 255)
	private String frontCameraResolution;
	
	@Column(name = "operatingSystem")
	@NotNull
	//@Size(min = 2,max = 255)
	private String operatingSystem;
	//
	@Column(name = "processorChip")
	@NotNull
	//@Size(min = 2,max = 255)
	private String processorChip;

	@Column(name = "cpuSpeed")
	@NotNull
	//@Size(min = 2,max = 255)
	private String cpuSpeed;

	@Column(name = "graphicsChip")
	@NotNull
	//@Size(min = 2,max = 255)
	private String graphicsChip;

	@Column(name = "ram")
	@NotNull
	//@Size(min = 2,max = 255)
	private String ram;

	@Column(name = "storageCapacity")
	@NotNull
	//@Size(min = 2,max = 255)
	private String storageCapacity;

	@Column(name = "remainingCapacity")
	@NotNull
	//@Size(min = 2,max = 255)
	private String remainingCapacity;

	@Column(name = "memoryStick")
	private String memoryStick;
	
	@Column(name = "phonebook")
	@NotNull
	//@Size(min = 2,max = 255)
	private String phonebook;
	
	@Column(name = "mobileNetwork")
	@NotNull
//	@Size(min = 2,max = 255)
	private String mobileNetwork;
	//
	@Column(name = "sim")
	@NotNull
	//@Size(min = 2,max = 255)
	private String sim;

	@Column(name = "wifi")
	@NotNull
	//@Size(min = 2,max = 255)
	private String wifi;

	@Column(name = "gps")
	@NotNull
	//@Size(min = 2,max = 255)
	private String gps;

	@Column(name = "bluetooth")
	@NotNull
	//@Size(min = 2,max = 255)
	private String bluetooth;

	@Column(name = "connectorChargingPort")
	@NotNull
	//@Size(min = 2,max = 255)
	private String connectorChargingPort;

	@Column(name = "headphoneJack")
	@NotNull
	//@Size(min = 2,max = 255)
	private String headphoneJack;

	@Column(name = "otherConnections")
	@NotNull
	//@Size(min = 2,max = 255)
	private String otherConnections;
	
	@Column(name = "batteryCapacity")
	@NotNull
	//@Size(min = 2,max = 255)
	private String batteryCapacity;
	
	@Column(name = "advancedSecurity")
	@NotNull
	//@Size(min = 2,max = 255)
	private String advancedSecurity;


	@Column(name = "design")
	@NotNull
	@Size(min = 2,max = 255)
	private String design;

	@Column(name = "materialSubstance")
	@NotNull
	//@Size(min = 2,max = 255)
	private String materialSubstance;

	@Column(name = "dimensionsWeight")
	@NotNull
	//@Size(min = 2,max = 255)
	private String dimensionsWeight;
	
	@Column(name = "insurance")
	@NotNull
	//@Size(min = 2,max = 255)
	private String insurance;
	
	@Column(name = "rearCameraResolution")
	@NotNull
	//@Size(min = 2,max = 255)
	private String rearCameraResolution;

//	@ManyToOne
//	@JoinColumn(name = "categoryId")
//	@JsonIgnoreProperties(value = { "aplications", "hibernateLazyInitializer" })
//	private Categories categorie;
//	
//	// bi-directional many-to-one association to Feedback
//	@OneToMany(mappedBy = "products")
//	@JsonIgnoreProperties(value = { "aplications", "hibernateLazyInitializer" })
//	private List<Feedback> feedbacks;
	
	

		//bi-directional many-to-one association to Category
		@ManyToOne
		@JoinColumn(name="categoryid", referencedColumnName = "id")
		@JsonIgnoreProperties(value = { "aplications", "hibernateLazyInitializer" })
		private Categories category;
		//bi-directional many-to-one association to Product
		@JsonIgnore
		@OneToMany(mappedBy = "product")
		private List<Feedback> feedback;

	

}
