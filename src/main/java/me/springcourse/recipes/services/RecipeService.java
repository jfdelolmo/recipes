package me.springcourse.recipes.services;

import me.springcourse.recipes.domain.jpa.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {

    List<Recipe> getRecipes();

    Optional<Recipe> getRecipe(long id);
}
