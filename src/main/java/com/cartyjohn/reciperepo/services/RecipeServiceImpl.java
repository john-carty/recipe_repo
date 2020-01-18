package com.cartyjohn.reciperepo.services;

import com.cartyjohn.reciperepo.commands.RecipeCommand;
import com.cartyjohn.reciperepo.model.RecipeEntity;
import com.cartyjohn.reciperepo.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<RecipeEntity> getRecipes() {
        Set<RecipeEntity> recipes = new HashSet<>();
        for (RecipeEntity recipeEntity : recipeRepository.findAll()) {
            recipes.add(recipeEntity);
        }
        return recipes;

    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
    return new RecipeCommand();
    }


}
