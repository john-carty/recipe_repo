package com.cartyjohn.reciperepo.controllers;

import com.cartyjohn.reciperepo.commands.IngredientCommand;
import com.cartyjohn.reciperepo.commands.RecipeCommand;
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
        model.addAttribute("ingredients", recipeService.findByRecipeCommandId(recipeId));
        return "recipe/ingredient/list";
    }
    // show one ingredient
    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}")
    public String showIngredient(@PathVariable Long recipeId, @PathVariable Long ingredientId, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeAndIngredientId(recipeId, ingredientId));
        return "recipe/ingredient/show";
    }

    // Make a new recipe ingredient - need to show form
    @GetMapping("/recipe/{recipeId}/ingredient/new")
        public String createIngredient(@PathVariable Long recipeId, Model model){
        RecipeCommand recipeCommand = recipeService.findByRecipeCommandId(recipeId);

        // make new Ingredient command, set recipeId and add it to the model;
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(recipeCommand.getId());
        model.addAttribute("ingredient", ingredientCommand);

        return "recipe/ingredient/ingredientForm";
        }

    // update an ingredient - need to show form with data in it
    @GetMapping("recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateIngredient(@PathVariable Long recipeId, @PathVariable Long ingredientId, Model model){
        IngredientCommand ingredientCommand = ingredientService.findByRecipeAndIngredientId(recipeId, ingredientId);
        model.addAttribute("ingredient", ingredientCommand);
        return "recipe/ingredient/ingredientForm";
    }

    // post/save a new ingredient - no form
    @PostMapping("recipe/{recipeId}/ingredient/")
    public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand){
        IngredientCommand savedIngredient = ingredientService.saveIngredientCommand(ingredientCommand);
        return "redirect:/recipe/" +savedIngredient.getRecipeId() + "/ingredient/" + savedIngredient.getId() + "show";
        }

    // delete an ingredient - get mapping?
    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable Long recipeId, @PathVariable Long ingredientId){
        ingredientService.deleteById(recipeId, ingredientId);

        // redirect back to ingredients page
        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
}
