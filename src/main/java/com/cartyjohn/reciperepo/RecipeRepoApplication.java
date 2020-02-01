package com.cartyjohn.reciperepo;

import com.cartyjohn.reciperepo.controllers.RecipeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class RecipeRepoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipeRepoApplication.class, args);
    }

}
