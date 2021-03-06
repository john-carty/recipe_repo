package com.cartyjohn.reciperepo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="ingredients")
public class IngredientEntity implements Serializable {

    private static final long serialVersionUID = 8342089103468789613L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String amount;

    @ManyToOne
    private RecipeEntity recipe;

    public IngredientEntity(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public RecipeEntity getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeEntity recipe) {
        this.recipe = recipe;
    }

}
