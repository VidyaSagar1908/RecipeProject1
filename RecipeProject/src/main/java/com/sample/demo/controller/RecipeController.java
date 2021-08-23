package com.sample.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.demo.beans.Recipe;
import com.sample.demo.dao.IngredientRepository;
import com.sample.demo.dao.RecipeRepository;
import com.sample.demo.exceptions.ResourceNotFoundException;
import com.sample.demo.util.LocalDateAndTime;

@RestController
public class RecipeController {

	@Autowired
	RecipeRepository recipeRepository;

	@Autowired
	IngredientRepository ingredientRepository;

	// List of recipes will fetch here
	@GetMapping(value = "/getRecipesList", headers = "Accept=application/json")
	public List<Recipe> getRecipesList() {
		return recipeRepository.findAll();

	}

	// Getting a Single Recipe
	@GetMapping(value = "/getSingleRecipe/{id}", headers = "Accept=application/json")
	public Recipe getSingleRecipe(@PathVariable int id) {

		Recipe recipe = recipeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recipe Not found with given id " + id));

		return recipe;
	}

	
	// Insert a recipe into the database
	@PostMapping("/insertRecipe")
	public Recipe insertRecipe(@Valid @RequestBody Recipe recipe) {

		LocalDateAndTime dateTime = new LocalDateAndTime();

		recipe.setCreationDate(dateTime.getLocalDateTime());

		return recipeRepository.save(recipe);

	}

	//Update the Recipe with id
	@PutMapping("/updateRecipe/{id}")
	public Recipe updateRecipe(@Valid @RequestBody Recipe recipe, @PathVariable int id) {

		Recipe existingRecipe = recipeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recipe Not found with given id " + id));

		existingRecipe.setRecipeName(recipe.getRecipeName());
		existingRecipe.setNoOfPeople(recipe.getNoOfPeople());
		existingRecipe.setIngredients(recipe.getIngredients());
		existingRecipe.setInstructions(recipe.getInstructions());
		existingRecipe.setRecipeType(recipe.getRecipeType());

		return recipeRepository.save(existingRecipe);

	}

	//Deleting a recipe with id
	@DeleteMapping("/deleteRecipe/{id}")
	public ResponseEntity<Recipe> deleteRecipe(@PathVariable int id) {

		Recipe existingRecipe = recipeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recipe Not found with given id " + id));

		recipeRepository.delete(existingRecipe);

		return ResponseEntity.ok().build();
	}

}
