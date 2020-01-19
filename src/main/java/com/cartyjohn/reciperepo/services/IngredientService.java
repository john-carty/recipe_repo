package com.cartyjohn.reciperepo.services;

import com.cartyjohn.reciperepo.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand saveIngredientCommand(IngredientCommand command) throws Exception;
    void deleteById(Long recipeId, Long ingredientId);

    IngredientCommand findIngredientCommandById(Long recipeId);
}
