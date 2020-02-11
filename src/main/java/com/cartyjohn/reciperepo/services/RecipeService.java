package com.cartyjohn.reciperepo.services;

import com.cartyjohn.reciperepo.commands.RecipeCommand;
import com.cartyjohn.reciperepo.model.RecipeEntity;

import java.util.Set;

public interface RecipeService {
    Set<RecipeCommand> getRecipes();
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    RecipeCommand findByRecipeCommandId(Long recipeId);
    void deleteById(Long recipeId);
    RecipeEntity findById(Long recipeId);

    RecipeCommand save(RecipeCommand recipeCommand);

    Set<RecipeCommand> getAllRecipes(Integer pageNumber, Integer numberResults);
}
