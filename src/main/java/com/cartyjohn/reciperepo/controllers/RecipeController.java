package com.cartyjohn.reciperepo.controllers;

import com.cartyjohn.reciperepo.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public String getIndexPage(Model model){
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }

    @GetMapping("/recipe/{recipeId}/show")
    public String showRecipe(@PathVariable Long recipeId, Model model){
        model.addAttribute("recipe", recipeService.findByRecipeCommandId(recipeId));
        return "recipe/show";
    }

    // forms for these
    //todo "/recipe/{recipeId}/update"

    //todo "/recipe/new"


    @GetMapping("/recipe/{recipeId}/delete")
    public String deleteRecipe(@PathVariable Long recipeId){
        recipeService.deleteById(recipeId);
        return "redirect:/";
    }
    //todo POST "recipe" - save/update

    // EXCEPTION HANDLER HERE
}

