package me.springcourse.recipes.domain.jpa;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@ToString(exclude = {"recipe"})
@EqualsAndHashCode(exclude = {"recipe"})
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String recipeNotes;

    @OneToOne
    private Recipe recipe;

}
