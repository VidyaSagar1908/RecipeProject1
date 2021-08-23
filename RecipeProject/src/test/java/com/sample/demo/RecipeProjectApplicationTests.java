package com.sample.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sample.demo.beans.Ingredient;
import com.sample.demo.beans.Recipe;
import com.sample.demo.controller.RecipeController;
import com.sample.demo.dao.RecipeRepository;
import com.sample.demo.exceptions.ResourceNotFoundException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RecipeProjectApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	RecipeController recipeController;

	@MockBean
	RecipeRepository recipeRepository;

	// Test case to check flow of getRecipesList method in controller to fetch list
	@Test
	public void getRecipeDetails() {
		List<Ingredient> ingredientList = new ArrayList<Ingredient>();
		Ingredient ing1 = new Ingredient(12, "wheat flour");
		Ingredient ing2 = new Ingredient(12, "wheat flour");
		ingredientList.add(ing1);
		ingredientList.add(ing2);
		List<Recipe> recipeList = Stream
				.of(new Recipe(28, "biscuits", "veg", 50, "21-08-2021 11:50",
						"1.take wheat flour,2.add water and sugar,3.cook it", ingredientList),
						new Recipe(32, "cakes", "veg", 40, "21-08-2021 20:50",
								"1.take wheat flour,2.add egg and sugar and water,3.cook it", ingredientList))
				.collect(Collectors.toList());
		Mockito.when(recipeRepository.findAll()).thenReturn(recipeList);
		assertEquals(recipeList, recipeController.getRecipesList());
	}

	/*
	 * Test case to check flow of getSingleRecipe method in controller to get single
	 * Recipe data
	 */
	@Test
	public void getRecipeWithId() {
		int recipeId = 25;
		Recipe recipeDataExpecting = new Recipe(43, "icecream", "veg", 5, "22-08-2021 11:50",
				"1.take batter,2.add water and salt,3.cook it", new ArrayList<Ingredient>());
		Mockito.when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipeDataExpecting));
		Recipe recipeDataActual = recipeController.getSingleRecipe(recipeId);

		Assertions.assertThat(recipeDataActual.getRecipeId()).isEqualTo(recipeDataExpecting.getRecipeId());
	}

	/*
	 * Test case to check else condition and through custom exception in
	 * getSingleRecipe
	 */
	@Test
	public void getRecipeWithIdException() {

		RecipeRepository recRepository = Mockito.mock(RecipeRepository.class);

		Mockito.when(recRepository.findById(0))
				.thenThrow(new ResourceNotFoundException("Recipe Not found with given id 0"));

		Throwable exception = assertThrows(ResourceNotFoundException.class, () -> recipeController.getSingleRecipe(0));

		assertEquals("Recipe Not found with given id 0", exception.getMessage());

	}

	// Test case to check flow of insertRecipe method to insert Recipe details
	@Test
	public void insertRecipe() {
		Recipe recipe = new Recipe(53, "icecream", "veg", 5, "22-08-2021 11:50",
				"1.take batter,2.add water and salt,3.cook it", new ArrayList<Ingredient>());
		Mockito.when(recipeRepository.save(recipe)).thenReturn(recipe);
		assertEquals(recipe, recipeController.insertRecipe(recipe));
	}

	// Test case to check flow of updareRecipe method to update Recipe Details

	/*
	 * @Test public void updateRecipe() { int recipeId = 43; Recipe
	 * recipeDataExpecting = new Recipe(43, "icecream", "veg", 5,
	 * "22-08-2021 11:50", "1.take batter,2.add water and salt,3.cook it", new
	 * ArrayList<Ingredient>());
	 * Mockito.when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(
	 * recipeDataExpecting));
	 * 
	 * 
	 * Recipe recipeDataActual = recipeController.updateRecipe(recipeDataExpecting,
	 * recipeId); System.out.println("recipeDataActual   " + recipeDataActual);
	 * Assertions.assertThat(recipeDataActual.getRecipeId()).isEqualTo(
	 * recipeDataExpecting.getRecipeId());
	 * 
	 * // assertEquals(recipeDataExpecting,
	 * recipeController.updateRecipe(recipeDataExpecting, 54));
	 * 
	 * }
	 */

	/*
	 * Test case to check else condition and through custom exception in
	 * updateRecipe
	 */
	@Test
	public void updateRecipeException() {
		Recipe recipe = new Recipe(0, null, null, 0, null, null, null);

		RecipeRepository recRepository = Mockito.mock(RecipeRepository.class);

		Mockito.when(recRepository.findById(0))
				.thenThrow(new ResourceNotFoundException("Recipe Not found with given id 0"));

		Throwable exception = assertThrows(ResourceNotFoundException.class,
				() -> recipeController.updateRecipe(recipe, 0));

		assertEquals("Recipe Not found with given id 0", exception.getMessage());

	}

	// Test case to check flow of deleteRecipe in Controller to Delete Recipe
	@Test
	public void deleteRecipe() {
		int recipeId = 43;
		Recipe recipeDataExpecting = new Recipe(43, "icecream", "veg", 5, "22-08-2021 11:50",
				"1.take batter,2.add water and salt,3.cook it", new ArrayList<Ingredient>());
		Mockito.when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipeDataExpecting));
		ResponseEntity<Recipe> recipeDataActual = recipeController.deleteRecipe(recipeId);
		System.out.println("recipeDataActual  " + recipeDataActual);
		Assertions.assertThat(recipeDataActual.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	/*
	 * Test case to check else condition and through custom exception in
	 * deleteRecipe
	 */
	@Test
	public void deleteRecipeException() {

		RecipeRepository recRepository = Mockito.mock(RecipeRepository.class);

		Mockito.when(recRepository.findById(0))
				.thenThrow(new ResourceNotFoundException("Recipe Not found with given id 0"));

		Throwable exception = assertThrows(ResourceNotFoundException.class, () -> recipeController.deleteRecipe(0));

		assertEquals("Recipe Not found with given id 0", exception.getMessage());

	}

}
