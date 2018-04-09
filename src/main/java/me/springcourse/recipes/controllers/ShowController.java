package me.springcourse.recipes.controllers;

import lombok.extern.slf4j.Slf4j;
import me.springcourse.recipes.domain.jpa.Recipe;
import me.springcourse.recipes.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Slf4j
@Controller
public class ShowController {

    private final RecipeService recipeService;

    public ShowController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping(value = {"recipe/show/{id}"}, method = RequestMethod.GET)
    public String showRecipe(Model model, @PathVariable long id){
        log.debug("Show recipe {} detail");

        String destinationPage = "recipe/show";

        Optional<Recipe> recipeOptional = recipeService.getRecipe(id);
        if (recipeOptional.isPresent())
            model.addAttribute("recipe", recipeOptional.get());
        else
            destinationPage = "notFound";

        return destinationPage;
    }

}