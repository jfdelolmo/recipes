package me.springcourse.recipes.services;

import me.springcourse.recipes.domain.jpa.Recipe;

import java.util.List;

@FunctionalInterface
public interface RecipeService {

    List<Recipe> getRecipes();
}
