package com.cartyjohn.reciperepo.services;

import com.cartyjohn.reciperepo.commands.RecipeCommand;
import com.cartyjohn.reciperepo.model.RecipeEntity;
import com.cartyjohn.reciperepo.repositories.RecipeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Set<RecipeCommand> getRecipes() {
        ModelMapper modelMapper = new ModelMapper();
        Set<RecipeCommand> recipes = new HashSet<>();
        for (RecipeEntity recipeEntity : recipeRepository.findAll()) {
            RecipeCommand recipeCommand = modelMapper.map(recipeEntity, RecipeCommand.class);
            recipes.add(recipeCommand);
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
    @Transactional
    public RecipeCommand findByRecipeCommandId(Long recipeId) {
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

    @Override
    public RecipeEntity findById(Long recipeId) {
        Optional<RecipeEntity> recipeEntity = recipeRepository.findById(recipeId);
        if(!recipeEntity.isPresent()){
            throw new RuntimeException("RecipeEntity not found");
            //todo make better exception
        }
        return recipeEntity.get();
    }

    @Override
    @Transactional
    public RecipeCommand save(RecipeCommand recipeCommand) {
        ModelMapper modelMapper = new ModelMapper();
        RecipeEntity recipeEntity = recipeRepository.save(modelMapper.map(recipeCommand, RecipeEntity.class));
        return modelMapper.map(recipeEntity, RecipeCommand.class);
    }

    @Override
    public Set<RecipeCommand> getAllRecipes(Integer pageNumber, Integer numberResults) {
        ModelMapper modelMapper = new ModelMapper();
        Pageable pageable = PageRequest.of(pageNumber, numberResults);
        Page<RecipeEntity> page = recipeRepository.findAll(pageable);
        Set<RecipeCommand> recipes = new HashSet<>();
        for (RecipeEntity recipeEntity : page) {
            RecipeCommand recipeCommand = modelMapper.map(recipeEntity, RecipeCommand.class);
            recipes.add(recipeCommand);
        }
        return recipes;
    }


}
