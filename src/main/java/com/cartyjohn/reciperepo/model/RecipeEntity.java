package com.cartyjohn.reciperepo.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name="recipes")
public class RecipeEntity implements Serializable {

    private static final long serialVersionUID = 375638467013305698L;
    //private List<String> ingredients;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable= false)
    private String description;

    @Column(nullable = false)
    @Size(max=5000)
    private String instructions;

    @Column(nullable = false)
    private String readyTime;

    @Column(nullable = false)
    private String occasion = "";

    @Column
    private String imageString;



    @Column(nullable = false)
    private boolean isHealthy;
    @Column(nullable = false)
    private boolean isGlutenFree;
    @Column(nullable = false)
    private boolean isVegan;
    @Column(nullable = false)
    private boolean isKeto;
    // add comments
    // add User
    @Column(nullable = false)
    private float rating = 0.0f;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<IngredientEntity> ingredients = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "recipes", fetch=FetchType.EAGER)
    private Set<TagEntity> tags = new HashSet<>();

    public RecipeEntity(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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



    public Set<IngredientEntity> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<IngredientEntity> ingredients) {
        this.ingredients = ingredients;
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    public Set<TagEntity> getTags() {
        return tags;
    }

    public void setTags(Set<TagEntity> tags) {
        this.tags = tags;
    }

    public void addTag(TagEntity tag){
        if(tag != null)
            this.tags.add(tag);
    }
    public void removeTag(TagEntity tag){
        if(tag != null)
         this.tags.remove(tag);
    }
}
