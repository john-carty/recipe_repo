package com.cartyjohn.reciperepo.repositories;

import com.cartyjohn.reciperepo.model.TagEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagRepository extends CrudRepository<TagEntity, Long> {
     List<TagEntity> findAll();
}
