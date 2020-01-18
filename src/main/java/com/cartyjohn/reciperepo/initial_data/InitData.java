package com.cartyjohn.reciperepo.initial_data;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.cartyjohn.reciperepo.model.RecipeEntity;
import com.cartyjohn.reciperepo.repositories.RecipeRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

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
            recipe.setDescription((String) jsonRecipe.get("description"));
            recipe.setInstructions((String) jsonRecipe.get("instructions"));
            recipe.setHealthy((boolean) jsonRecipe.get("veryHealthy"));
            recipe.setVegan((boolean) jsonRecipe.get("vegan"));
            recipe.setGlutenFree((boolean) jsonRecipe.get("glutenFree"));
            recipe.setKeto((boolean) jsonRecipe.get("ketogenic"));
            System.out.println(jsonRecipe.get("readyInMinutes").toString());
            recipe.setReadyTime(jsonRecipe.get("readyInMinutes").toString());
            recipe.setOccasion((String) jsonRecipe.get("occasion"));

            recipes.add(recipe);


        }
        recipeRepository.saveAll(recipes);
    }catch(Exception ex){
        ex.printStackTrace();
    }
        return recipes;
    }
}
