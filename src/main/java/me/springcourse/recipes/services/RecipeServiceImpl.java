package me.springcourse.recipes.services;

import lombok.extern.slf4j.Slf4j;
import me.springcourse.recipes.domain.jpa.Recipe;
import me.springcourse.recipes.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getRecipes(){
        log.debug("Get all recipes");
        List<Recipe> recipes = new ArrayList<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }


    public Optional<Recipe> getRecipe(long id){
        log.debug("I'm at service looking for recipe with id {}", id);

         return recipeRepository.findById(id);
    }
}
