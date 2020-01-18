package com.cartyjohn.reciperepo.repositories;

import com.cartyjohn.reciperepo.model.RecipeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends PagingAndSortingRepository<RecipeEntity, Long> {
    Page<RecipeEntity> findAll(Pageable pageable);
    //Page<RecipeEntity> findByCategory(String category);



}
