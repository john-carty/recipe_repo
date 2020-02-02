package com.cartyjohn.reciperepo.initial_data;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.cartyjohn.reciperepo.model.IngredientEntity;
import com.cartyjohn.reciperepo.model.RecipeEntity;
import com.cartyjohn.reciperepo.repositories.RecipeRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import java.io.FileReader;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

/* Initialize data from a text file containing JSON
*  on every Context Refresh */
@Component
public class InitData implements ApplicationListener<ContextRefreshedEvent> {
private RecipeRepository recipeRepository;

    public InitData(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getData());
    }

    List<RecipeEntity> getData(){
        List<RecipeEntity> recipes = new ArrayList<>();
    JSONParser parser = new JSONParser();
    try {
     Object object =
              parser.parse(new FileReader("C:\\Users\\JCart\\Dropbox\\_U_D_E_M_Y\\JAVA_MASTERCLASS\\recipe-repo\\src\\main\\java\\com\\cartyjohn\\reciperepo\\initial_data\\recipe_json.txt"));
        JSONObject jsonObject = (JSONObject) object;
        JSONArray jsonArray = (JSONArray) jsonObject.get("recipes");
        for(Object obj : jsonArray){
            RecipeEntity recipe = new RecipeEntity();
            JSONObject jsonRecipe = (JSONObject) obj;
            recipe.setDescription((String) jsonRecipe.get("title"));
            recipe.setInstructions((String) jsonRecipe.get("instructions"));
            recipe.setImageString((String) jsonRecipe.get("image"));
            recipe.setHealthy((boolean) jsonRecipe.get("veryHealthy"));
            recipe.setVegan((boolean) jsonRecipe.get("vegan"));
            recipe.setGlutenFree((boolean) jsonRecipe.get("glutenFree"));
            recipe.setKeto((boolean) jsonRecipe.get("ketogenic"));
            recipe.setReadyTime(jsonRecipe.get("readyInMinutes").toString());
            if(jsonRecipe.get("occasion")!=null)
                recipe.setOccasion((String) jsonRecipe.get("occasion"));

            // get ingredients
            JSONArray jsonIngredients = (JSONArray) jsonRecipe.get("extendedIngredients");
            Set<IngredientEntity> ingredients = new HashSet<>();
                for(Object ingredientObj : jsonIngredients){
                    JSONObject jsonIngredient = (JSONObject) ingredientObj;

                    IngredientEntity ingredient = new IngredientEntity();
                    ingredient.setAmount( jsonIngredient.get("amount") + " " + jsonIngredient.get("unit"));
                    ingredient.setDescription((String) jsonIngredient.get("name"));
                    ingredient.setRecipe(recipe);
                    ingredients.add(ingredient);
                }
                recipe.getIngredients().addAll(ingredients);
            recipes.add(recipe);


        }
        recipeRepository.saveAll(recipes);
    }catch(Exception ex){
        ex.printStackTrace();
    }
        return recipes;
    }
}
