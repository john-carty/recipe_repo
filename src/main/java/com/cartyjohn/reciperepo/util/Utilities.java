package com.cartyjohn.reciperepo.util;

import java.security.SecureRandom;
import java.util.Random;

public class Utilities {
    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public String generateRecipeId(int length){
        return generateRandomId(length);
    }
    public String generateIngredientId(int length){
        return generateRandomId(length);
    }

    private String generateRandomId(int length){
        StringBuilder result = new StringBuilder(length);

        for(int i = 0; i < length; ++i){
            result.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));

        }
        return new String(result);

    }
}
