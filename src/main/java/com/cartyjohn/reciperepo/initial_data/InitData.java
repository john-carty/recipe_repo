package com.cartyjohn.reciperepo.initial_data;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.cartyjohn.reciperepo.model.IngredientEntity;
import com.cartyjohn.reciperepo.model.RecipeEntity;
import com.cartyjohn.reciperepo.model.TagEntity;
import com.cartyjohn.reciperepo.repositories.RecipeRepository;
import com.cartyjohn.reciperepo.repositories.TagRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/* Initialize data from a text file containing JSON
*  on every Context Refresh */
@Component
public class InitData implements ApplicationListener<ContextRefreshedEvent> {
private RecipeRepository recipeRepository;
private TagRepository tagRepository;

    public InitData(RecipeRepository recipeRepository, TagRepository tagRepository) {
        this.recipeRepository = recipeRepository;
        this.tagRepository = tagRepository;
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
        Map<String, TagEntity> tagEntities = new HashMap<String, TagEntity>();
        for(Object obj : jsonArray){
            RecipeEntity recipe = new RecipeEntity();
            JSONObject jsonRecipe = (JSONObject) obj;
            recipe.setDescription((String) jsonRecipe.get("title"));
            recipe.setInstructions((String) jsonRecipe.get("instructions"));
            // get image url
            String imgUrl=((String) jsonRecipe.get("image"));
            // get filename from url
            String imageString =imgUrl.split("/")[4];
            recipe.setImageString(imageString);
            // download image - first time only
            // downloadImage(imgUrl, imageString);


            recipe.setHealthy((boolean) jsonRecipe.get("veryHealthy"));
            recipe.setVegan((boolean) jsonRecipe.get("vegan"));
            recipe.setGlutenFree((boolean) jsonRecipe.get("glutenFree"));
            recipe.setKeto((boolean) jsonRecipe.get("ketogenic"));
            recipe.setReadyTime(jsonRecipe.get("readyInMinutes").toString());
            // get occasions and make them tags
            JSONArray jsonOccasions = (JSONArray) jsonRecipe.get("occasions");
            if(jsonOccasions != null) {
                for(Object occasionObj : jsonOccasions){
                    String desc = (String)occasionObj;
                    TagEntity tag;
                    // if tag is already in hashTable, use that one, else make a new tag
                    if(tagEntities.containsKey(desc)){
                        tag= tagEntities.get(desc);
                    }
                    else{
                        tag = new TagEntity();
                        tag.setDescription(desc);
                        tagEntities.put(desc, tag);
                    }

                    tag.addRecipe(recipe);
                    recipe.addTag(tag);
                }
            }

            // get cuisunes and make them tags
            JSONArray jsonCuisines = (JSONArray) jsonRecipe.get("cuisines");
            if(jsonCuisines != null) {
                for(Object cuisineObj : jsonCuisines){
                    String desc = (String)cuisineObj;
                    TagEntity tag;
                    // if tag is already in hashTable, use that one, else make a new tag
                    if(tagEntities.containsKey(desc)){
                        tag= tagEntities.get(desc);
                    }
                    else{
                        tag = new TagEntity();
                        tag.setDescription(desc);
                        tagEntities.put(desc, tag);
                    }

                    tag.addRecipe(recipe);
                    recipe.addTag(tag);
                }
            }

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

    public void downloadImage(String imageURL, String fileName){
        BufferedImage image = null;

        try {
            URL url = new URL(imageURL);

            //create file to store image
            File outputImageFile = new File("src/main/resources/static/img/recipe/" + fileName);
            image = ImageIO.read(url);

            //store image to file
            ImageIO.write(image, "jpg", outputImageFile);

        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}
