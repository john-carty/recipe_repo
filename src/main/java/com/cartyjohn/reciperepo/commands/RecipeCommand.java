package com.cartyjohn.reciperepo.commands;
import java.io.Serializable;
import java.util.Set;


public class RecipeCommand {

    private long id;

    private String description;

    private String instructions;

    private String readyTime;

    private String occasion = "";

    private boolean isHealthy;
    private boolean isGlutenFree;
    private boolean isVegan;
    private boolean isKeto;
    // add comments
    // add User
    private float rating;
    private Set<IngredientCommand> ingredients;
    public RecipeCommand() {
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getReadyTime() {
        return readyTime;
    }

    public void setReadyTime(String readyTime) {
        this.readyTime = readyTime;
    }

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    public boolean isHealthy() {
        return isHealthy;
    }

    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }

    public boolean isGlutenFree() {
        return isGlutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {
        isGlutenFree = glutenFree;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public void setVegan(boolean vegan) {
        isVegan = vegan;
    }

    public boolean isKeto() {
        return isKeto;
    }

    public void setKeto(boolean keto) {
        isKeto = keto;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<IngredientCommand> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<IngredientCommand> ingredients) {
        this.ingredients = ingredients;
    }
}
