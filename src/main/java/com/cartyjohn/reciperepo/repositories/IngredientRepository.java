package com.cartyjohn.reciperepo.repositories;

import com.cartyjohn.reciperepo.model.IngredientEntity;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<IngredientEntity, Long> {

}
