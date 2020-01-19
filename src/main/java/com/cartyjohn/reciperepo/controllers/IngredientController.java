package com.cartyjohn.reciperepo.controllers;

import com.cartyjohn.reciperepo.commands.IngredientCommand;
import com.cartyjohn.reciperepo.services.IngredientService;
import com.cartyjohn.reciperepo.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IngredientController {
    private IngredientService ingredientService;
    private RecipeService recipeService;

    public IngredientController(IngredientService ingredientService, RecipeService recipeService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
    }

    // list ingredients for a recipe
    @GetMapping("/recipe/{recipeId}/ingredients")
    public String getRecipeIngredients(@PathVariable Long recipeId, Model model){
        model.addAttribute("ingredients", ingredientService.findIngredientCommandById(recipeId));
        return "recipe/ingredient/list";
    }
    // show one ingredient
    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}")
    public String showIngredient(@PathVariable Long recipeId, @PathVariable Long ingredientId, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeAndIngredientId());
        return "recipe/ingredient/show";
    }

    // update a recipe ingredient - need to show update form
    // post a new ingredient - need to show form
    // post/save a new ingredient - no form

    @PostMapping("recipe/{recipeId}/ingredient/")
    public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand){
        IngredientCommand savedIngredient = ingredientService.saveIngredientCommand(ingredientCommand);

        }

    // delete an ingredient - get mapping?
    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable Long recipeId, @PathVariable Long ingredientId){
        ingredientService.deleteById(recipeId, ingredientId);

        // redirect back to ingredients page
        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
}
