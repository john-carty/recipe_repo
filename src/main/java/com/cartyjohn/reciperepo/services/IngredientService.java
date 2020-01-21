package com.cartyjohn.reciperepo.services;

import com.cartyjohn.reciperepo.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand saveIngredientCommand(IngredientCommand command);
    void deleteById(Long recipeId, Long ingredientId);
    IngredientCommand findByRecipeAndIngredientId(Long recipeId, Long ingredientId);
}
