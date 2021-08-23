package com.sample.demo.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name = "recipe")
public class Recipe {

	@Id
	@Column(name = "rec_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int recipeId;

	@NotNull
	@NotBlank
	@Size(min = 3, message = "Recipe Name should be atlease 3 characters")
	@Column(name = "rec_name")
	private String recipeName;

	@NotNull
	@NotBlank
	@Size(min = 3, max = 6, message = "Enter Recipe Type either veg or nonveg")
	@Column(name = "rec_type")
	private String recipeType;

	@Min(value = 1, message = "Enter No Of People should be minimum 1 person")
	@Column(name = "no_of_people")
	private int noOfPeople;

	@Column(name = "rec_creation_time")
	private String creationDate;

	@NotNull
	@NotBlank
	@Column(name = "instructions")
	private String instructions;

	@OneToMany(targetEntity = Ingredient.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "rec_id", referencedColumnName = "rec_id")
	private List<Ingredient> ingredients;

	@Override
	public String toString() {
		return "Recipe [recipeId=" + recipeId + ", recipeName=" + recipeName + ", recipeType=" + recipeType
				+ ", noOfPeople=" + noOfPeople + ", creationDate=" + creationDate + ", instructions=" + instructions
				+ ", ingredients=" + ingredients + "]";
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getRecipeType() {
		return recipeType;
	}

	public void setRecipeType(String recipeType) {
		this.recipeType = recipeType;
	}

	public int getNoOfPeople() {
		return noOfPeople;
	}

	public void setNoOfPeople(int noOfPeople) {
		this.noOfPeople = noOfPeople;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Recipe(int recipeId,
			@NotNull @NotBlank @Size(min = 3, message = "Recipe Name should be atlease 3 characters") String recipeName,
			@NotNull @NotBlank @Size(min = 3, max = 6, message = "Enter Recipe Type either veg or nonveg") String recipeType,
			@Min(value = 1, message = "Enter No Of People should be minimum 1 person") int noOfPeople,
			String creationDate, @NotNull @NotBlank String instructions, List<Ingredient> ingredients) {
		super();
		this.recipeId = recipeId;
		this.recipeName = recipeName;
		this.recipeType = recipeType;
		this.noOfPeople = noOfPeople;
		this.creationDate = creationDate;
		this.instructions = instructions;
		this.ingredients = ingredients;
	}
	
	public Recipe() {
		
	}

}
