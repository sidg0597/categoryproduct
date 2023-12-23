package com.categoryProduct.repositories;

import org.springframework.data.repository.CrudRepository;

import com.categoryProduct.models.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{

}
