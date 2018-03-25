package me.springcourse.recipes.domain.jpa;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    private final String CATEGORY_NAME = "CATEGORY_NAME";
    private final Long CATEGORY_ID = 5L;

    Category category;

    @Before
    public void setup(){
        category = new Category();
    }

    @Test
    public void getRecipes() {
        assertNotNull(category.getRecipes());
    }

    @Test
    public void getCategoryName() {
        category.setCategoryName(CATEGORY_NAME);
        assertEquals(CATEGORY_NAME, category.getCategoryName());
    }

    @Test
    public void getCategoryId(){
        category.setId(CATEGORY_ID);
        assertEquals(CATEGORY_ID, category.getId());
    }

}