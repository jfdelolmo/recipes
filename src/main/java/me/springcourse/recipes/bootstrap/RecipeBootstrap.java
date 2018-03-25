package me.springcourse.recipes.bootstrap;

import lombok.extern.slf4j.Slf4j;
import me.springcourse.recipes.domain.Difficulty;
import me.springcourse.recipes.domain.jpa.*;
import me.springcourse.recipes.repositories.CategoryRepository;
import me.springcourse.recipes.repositories.RecipeRepository;
import me.springcourse.recipes.repositories.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private RecipeRepository recipeRepository;

   @Autowired
    public RecipeBootstrap(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes(){
        log.debug("Boostrapping recipes");

        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> eachUnitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Each");

        if (!eachUnitOfMeasureOptional.isPresent())
            throw new RuntimeException("Expected UOM not found");

        Optional<UnitOfMeasure> tablespoonUnitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if (!tablespoonUnitOfMeasureOptional.isPresent())
            throw new RuntimeException("Expected UOM not found");

        Optional<UnitOfMeasure> teaspoonUnitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if (!teaspoonUnitOfMeasureOptional.isPresent())
            throw new RuntimeException("Expected UOM not found");

        Optional<UnitOfMeasure> dashUnitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Dash");

        if (!dashUnitOfMeasureOptional.isPresent())
            throw new RuntimeException("Expected UOM not found");

        Optional<UnitOfMeasure> pintUnitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Pint");

        if (!pintUnitOfMeasureOptional.isPresent())
            throw new RuntimeException("Expected UOM not found");

        Optional<UnitOfMeasure> cupUnitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Cup");

        if (!cupUnitOfMeasureOptional.isPresent())
            throw new RuntimeException("Expected UOM not found");

        //Get optionals
        UnitOfMeasure each =  eachUnitOfMeasureOptional.get();
        UnitOfMeasure tablespoon = tablespoonUnitOfMeasureOptional.get();
        UnitOfMeasure teaspoon = teaspoonUnitOfMeasureOptional.get();
        UnitOfMeasure dash = dashUnitOfMeasureOptional.get();
        UnitOfMeasure pint = pintUnitOfMeasureOptional.get();
        UnitOfMeasure cup = cupUnitOfMeasureOptional.get();

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByCategoryName("Mexican");

        if (!mexicanCategoryOptional.isPresent())
            throw new RuntimeException("Expected Category not found");

        Optional<Category> americanCategoryOptional = categoryRepository.findByCategoryName("American");

        if (!americanCategoryOptional.isPresent())
            throw new RuntimeException("Expected Category not found");

        //Get categories
        Category mexican = mexicanCategoryOptional.get();
        Category american = americanCategoryOptional.get();

        //Yummy recipes
        Recipe guacamoleRecipe = new Recipe();

        guacamoleRecipe.getCategories().add(mexican);
        guacamoleRecipe.getCategories().add(american);

        guacamoleRecipe.setCookTime(0);
        guacamoleRecipe.setDescription("Perfect guacamole");
        guacamoleRecipe.setDifficulty(Difficulty.EASY);
        guacamoleRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");

        guacamoleRecipe.addIngredient(new Ingredient("Ripe Avocado", new BigDecimal(2), each));
        guacamoleRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(0.5), teaspoon));
        guacamoleRecipe.addIngredient(new Ingredient("Fresh lime juice or lemon juice", new BigDecimal(1), tablespoon));
        guacamoleRecipe.addIngredient(new Ingredient("Minced red onion or thinly sliced green onion", new BigDecimal(0.25), cup));
        guacamoleRecipe.addIngredient(new Ingredient("Serrano chiles, stems and seeds removed, minced", new BigDecimal(1), each));
        guacamoleRecipe.addIngredient(new Ingredient("Cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), tablespoon));
        guacamoleRecipe.addIngredient(new Ingredient("Freshly grated black pepper", new BigDecimal(2), dash));
        guacamoleRecipe.addIngredient(new Ingredient("Ripe tomato, seeds and pulp removed, chopped", new BigDecimal(0.5), each));


        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!");

        guacamoleRecipe.setNotes(guacamoleNotes);
        guacamoleRecipe.setPrepTime(10);
        guacamoleRecipe.setServings(4);
        guacamoleRecipe.setSource("Simply Recipes");
        guacamoleRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");

        recipes.add(guacamoleRecipe);

        //Tacos recipe
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Tacos");
        tacosRecipe.getCategories().add(mexican);
        tacosRecipe.getCategories().add(american);

        Notes tacosNotes = new Notes();
        tacosNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "\n" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!");

        tacosRecipe.setNotes(tacosNotes);

        tacosRecipe.setPrepTime(20);
        tacosRecipe.setCookTime(15);
        tacosRecipe.setServings(4);
        tacosRecipe.setSource("Simply Recipes");
        tacosRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

        tacosRecipe.addIngredient(new Ingredient("Ancho chili powder", new BigDecimal(2), tablespoon));
        tacosRecipe.addIngredient(new Ingredient("Dried oregano", new BigDecimal(1), teaspoon));
        tacosRecipe.addIngredient(new Ingredient("Dried cumin", new BigDecimal(1), teaspoon));
        tacosRecipe.addIngredient(new Ingredient("Sugarr", new BigDecimal(1), teaspoon));
        tacosRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(0.5), teaspoon));
        tacosRecipe.addIngredient(new Ingredient("Clove garlic, finely chopped", new BigDecimal(1), each));
        tacosRecipe.addIngredient(new Ingredient("Finely grated orange zest", new BigDecimal(2), tablespoon));
        tacosRecipe.addIngredient(new Ingredient("Fresh-squeezed orange juice", new BigDecimal(2), tablespoon));
        tacosRecipe.addIngredient(new Ingredient("Olive oil", new BigDecimal(2), tablespoon));
        tacosRecipe.addIngredient(new Ingredient("Skinless, boneless chicken thighs (1 1/4 pounds)", new BigDecimal(4), each));

        tacosRecipe.setDifficulty(Difficulty.MODEARTE);

        tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");

        recipes.add(tacosRecipe);

        return recipes;
   }


}
