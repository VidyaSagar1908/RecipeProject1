package com.sample.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.demo.beans.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer>{

}
