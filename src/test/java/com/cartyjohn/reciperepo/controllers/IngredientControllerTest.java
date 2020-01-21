package com.cartyjohn.reciperepo.controllers;

import com.cartyjohn.reciperepo.services.IngredientService;
import com.cartyjohn.reciperepo.services.RecipeService;
import org.junit.Test;
import org.mockito.Mock;
import org.junit.Before;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IngredientControllerTest {

    @Mock
    RecipeService recipeService;
    @Mock
    IngredientService ingredientService;

    MockMvc mockMvc;

    IngredientController ingredientController;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        ingredientController = new IngredientController( ingredientService, recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    public void deleteIngredientTest() throws Exception {
        // test the path and make sure status is 3xxredirection
        mockMvc.perform(get("/recipe/123/ingredient/456/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/123/ingredients"));
        verify(ingredientService, times(1)).deleteById(anyLong(), anyLong());

    }
}