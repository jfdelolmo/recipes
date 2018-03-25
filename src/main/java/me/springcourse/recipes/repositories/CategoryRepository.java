package me.springcourse.recipes.repositories;

import me.springcourse.recipes.domain.jpa.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {


    Optional<Category> findByCategoryName(String categoryName);

}
