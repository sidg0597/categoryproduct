package com.categoryProduct.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.categoryProduct.models.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>,JpaRepository<Product, Integer>{

	Page<Product> findByCategoryId(int categoryId, Pageable pageable);
}
