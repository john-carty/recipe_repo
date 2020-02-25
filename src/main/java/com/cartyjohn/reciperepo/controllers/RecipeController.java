package com.cartyjohn.reciperepo.controllers;

import com.cartyjohn.reciperepo.commands.RecipeCommand;
import com.cartyjohn.reciperepo.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public String getIndexPage(Model model){
        model.addAttribute("recipes", recipeService.getMostRecent9Recipes());
        return "index";
    }
    @GetMapping("/showRecipes/{pageNumber}")
        public String getAllRecipes(@PathVariable Integer pageNumber,  Model model){
        model.addAttribute("recipes", recipeService.getAllRecipes(pageNumber, 9));
        return "index";
        }

    @GetMapping("/recipe/{recipeId}/show")
    public String showRecipe(@PathVariable Long recipeId, Model model){
        model.addAttribute("recipe", recipeService.findByRecipeCommandId(recipeId));
        return "recipe/show";
    }

    // return form to update existing recipe
    @GetMapping("/recipe/{recipeId}/update")
    public String updateRecipe(@PathVariable Long recipeId, Model model){
        System.out.println(recipeId);
        RecipeCommand recipeCommand = recipeService.findByRecipeCommandId(recipeId);
        model.addAttribute("recipe", recipeCommand);
        System.out.println(recipeCommand.getId());
        return "forms/recipeEditForm";
    }

    // return form to create new recipe
    @GetMapping("/recipe/new")
    public String createRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return "forms/newRecipeForm";
    }


    @GetMapping("/recipe/{recipeId}/delete")
    public String deleteRecipe(@PathVariable Long recipeId){
        recipeService.deleteById(recipeId);
        return "redirect:/";
    }

    @PostMapping("/recipe")
    public String saveORecipe(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "recipe/newRecipeForm";
        }
        RecipeCommand savedRecipe  = recipeService.save(recipeCommand);

        // send to update to add directions/ingredients
        return "redirect:/recipe/" + savedRecipe.getId() + "/update";
    }
    // todo EXCEPTION HANDLER
    @PatchMapping("/recipe")
    public String updateRecipe(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "recipe/RecipeEditForm";
        }
        RecipeCommand savedRecipe  = recipeService.save(recipeCommand);

        // send to update to add directions/ingredients
        return "redirect:/recipe/" + savedRecipe.getId() + "/show";
    }
}

