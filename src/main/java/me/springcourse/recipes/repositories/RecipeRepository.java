package me.springcourse.recipes.repositories;

import me.springcourse.recipes.domain.jpa.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,  Long> {
}
