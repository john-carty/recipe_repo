package com.cartyjohn.reciperepo.commands;

import java.util.Date;

public class CommentCommand {

    private Long id;

    private Date createdAt;
    private Date updatedAt;
    private UserCommand userCommand;
    private RecipeCommand recipeCommand;
    private String content;
    public CommentCommand(){}
    public RecipeCommand getRecipeCommand() {
        return recipeCommand;
    }

    public void setRecipeCommand(RecipeCommand recipeCommand) {
        this.recipeCommand = recipeCommand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserCommand getUserCommand() {
        return userCommand;
    }

    public void setUserCommand(UserCommand userCommand) {
        this.userCommand = userCommand;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
