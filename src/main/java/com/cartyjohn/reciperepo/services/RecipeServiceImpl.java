package com.cartyjohn.reciperepo.services;

import com.cartyjohn.reciperepo.commands.IngredientCommand;
import com.cartyjohn.reciperepo.commands.RecipeCommand;
import com.cartyjohn.reciperepo.model.RecipeEntity;
import com.cartyjohn.reciperepo.repositories.RecipeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

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

    // Convert to and save RecipeEntity to db
    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        ModelMapper modelMapper = new ModelMapper();
        RecipeEntity recipeToSave = modelMapper.map(command, RecipeEntity.class);
        RecipeEntity savedRecipe = recipeRepository.save(recipeToSave);
        return modelMapper.map(savedRecipe, RecipeCommand.class);
    }

    @Override
    public RecipeCommand findById(Long recipeId) {
        Optional<RecipeEntity> recipeEntity = recipeRepository.findById((recipeId));
        if(!recipeEntity.isPresent()){
            System.out.println("Not found, throw error here.");
        }
        ModelMapper modelMapper = new ModelMapper();
        RecipeCommand recipeCommand = modelMapper.map(recipeEntity, RecipeCommand.class);

        return recipeCommand;
    }

    @Override
    public void deleteById(Long recipeId) {
        recipeRepository.deleteById(recipeId);
    }


}
