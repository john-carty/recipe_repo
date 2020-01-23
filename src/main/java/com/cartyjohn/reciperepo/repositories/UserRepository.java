package com.cartyjohn.reciperepo.repositories;

import com.cartyjohn.reciperepo.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
