package com.cartyjohn.reciperepo.services;

import com.cartyjohn.reciperepo.commands.IngredientCommand;
import com.cartyjohn.reciperepo.commands.RecipeCommand;
import com.cartyjohn.reciperepo.model.IngredientEntity;
import com.cartyjohn.reciperepo.model.RecipeEntity;
import com.cartyjohn.reciperepo.repositories.IngredientRepository;
import com.cartyjohn.reciperepo.repositories.RecipeRepository;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
public class IngredientServiceImpl implements IngredientService {

    private IngredientRepository ingredientRepository;
    private RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, RecipeRepository recipeRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command){
        ModelMapper modelMapper = new ModelMapper();
        Optional<RecipeEntity> recipeOptional = recipeRepository.findById(command.getRecipeId());
        if(!recipeOptional.isPresent()){
            // todo create NotFound Exception
            throw new RuntimeException("Recipe id not found for recipe id: " +    command.getRecipeId());
        }
        else{
            // get recipe
            RecipeEntity recipeEntity = recipeOptional.get();

            IngredientEntity ingredientEntity = null;
            // search for ingredient
            for(IngredientEntity ingredient : recipeEntity.getIngredients()){
                if(ingredient.getId() == command.getId()){
                    ingredientEntity=ingredient;
                    break;
                }
            }

            if(ingredientEntity != null){
                // found - update the ingredient
                ingredientEntity.setDescription(command.getDescription());
                ingredientEntity.setAmount(command.getAmount());
            }
            else{
                // not found - add new ingredient
                IngredientEntity newIngredient = modelMapper.map(command, IngredientEntity.class);
                ingredientEntity.setRecipe(recipeEntity);
                recipeEntity.getIngredients().add(ingredientEntity);
            }

            // now save recipe
            RecipeEntity savedRecipe = recipeRepository.save(recipeEntity);

            // find saved ingredient and send back command
            IngredientEntity savedIngredient = null;
            for(IngredientEntity ingredient : savedRecipe.getIngredients()){
                if(ingredient.getId() == command.getId()){
                    // found
                    savedIngredient = ingredient;
                    break;
                }
            }
            if(savedIngredient == null) {
                // todo create NOtFoundException
                throw new RuntimeException("Saved Ingredient Not found");
            }
            return modelMapper.map(savedIngredient, IngredientCommand.class);

        }
    }

    @Override
    public void deleteById(Long recipeId, Long ingredientId) {
    // find recipe

        // if present, find the ingredient in the recipe

        // if that is present, set the ingredients recipe to null,
        // remove from recipes ingredient list and save recipe
    }

    @Override
    public IngredientCommand findByRecipeAndIngredientId(Long recipeId, Long ingredientId) {

        // Find recipeEntity, if not null, find the ingredient
        Optional<RecipeEntity> recipeOptional = recipeRepository.findById(recipeId);
        RecipeEntity recipeEntity = null;
        if(!recipeOptional.isPresent()){
            // throw error here
            System.out.println("recipe not found");
        }
        else{
            recipeEntity = recipeOptional.get();
        }
        //find and return the ingredient
        IngredientEntity ingredientEntity = null;
        for(IngredientEntity ingredient : recipeEntity.getIngredients()){
            if(ingredient.getId() == ingredientId){
                ingredientEntity = ingredient;
                break;
            }
        }
        if(ingredientEntity == null){
            System.out.println("Not found - ingredient");
            throw new RuntimeException("Ingredient not found");
            // todo implement better exception
        }

        // convert and return
        return new ModelMapper().map(ingredientEntity, IngredientCommand.class);
    }


}
