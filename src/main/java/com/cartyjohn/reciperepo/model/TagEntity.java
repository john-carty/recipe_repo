package com.cartyjohn.reciperepo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name="tags")
public class TagEntity implements Serializable {
    private static final long serialVersionUID = 1128307516654986978L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RecipeEntity> recipes = new HashSet<>();

    public TagEntity() {
    }


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
