package com.cartyjohn.reciperepo.services;

import com.cartyjohn.reciperepo.commands.IngredientCommand;
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

    }

    @Override
    public IngredientCommand findIngredientCommandById(Long recipeId) {
        return null;
    }


}
