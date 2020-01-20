package com.cartyjohn.reciperepo.services;

import com.cartyjohn.reciperepo.commands.RecipeCommand;
import com.cartyjohn.reciperepo.model.RecipeEntity;

import java.util.Set;

public interface RecipeService {
    Set<RecipeEntity> getRecipes();
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    RecipeCommand findById(Long recipeId);
    void deleteById(Long recipeId);

}
