package com.sample.demo.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ingredient")
public class Ingredient {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ingId;

	@NotNull
	@NotBlank
	@Size(min = 3, message = "Ingredient Name should be atlease 3 characters")
	@Column(name = "name")
	private String name;

	public int getIngId() {
		return ingId;
	}

	public void setIngId(int ingId) {
		this.ingId = ingId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Ingredient [ingId=" + ingId + ", name=" + name + "]";
	}

	public Ingredient(int ingId,
			@NotNull @NotBlank @Size(min = 3, message = "Ingredient Name should be atlease 3 characters") String name) {
		super();
		this.ingId = ingId;
		this.name = name;
	}

	public Ingredient() {
		
	}
}
