package com.cartyjohn.reciperepo.model;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Set;

@Entity(name="tags")
public class TagEntity implements Serializable {
    private static final long serialVersionUID = 1128307516654986978L;

    private String description;


    private Set<RecipeEntity> recipes;

    public TagEntity() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<RecipeEntity> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<RecipeEntity> recipes) {
        this.recipes = recipes;
    }

    public void addRecipe(RecipeEntity recipe){
        this.recipes.add(recipe);
    }
    public void removeRecipe(RecipeEntity recipe){
        this.recipes.remove(recipe);
    }
}
