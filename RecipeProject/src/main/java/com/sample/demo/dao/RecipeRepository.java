package com.sample.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.demo.beans.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Integer>{

}
