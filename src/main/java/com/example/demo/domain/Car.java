package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cars")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String manufacurer;
	private String model;
	private double cc;
	private Integer puertas;
	
	public Car() {
		
	}
	
	public Car(Long id, String manufacurer, String model, double cc, Integer puertas) {
		super();
		this.id = id;
		this.manufacurer = manufacurer;
		this.model = model;
		this.cc = cc;
		this.puertas = puertas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getManufacurer() {
		return manufacurer;
	}

	public void setManufacurer(String manufacurer) {
		this.manufacurer = manufacurer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getCc() {
		return cc;
	}

	public void setCc(double cc) {
		this.cc = cc;
	}

	public Integer getPuertas() {
		return puertas;
	}

	public void setPuertas(Integer puertas) {
		this.puertas = puertas;
	}

	
}
