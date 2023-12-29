package com.categoryProduct.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.categoryProduct.models.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer>,JpaRepository<Category, Integer>{

}
