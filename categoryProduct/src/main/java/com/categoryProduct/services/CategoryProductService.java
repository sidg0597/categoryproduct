package com.categoryProduct.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.categoryProduct.models.Category;
import com.categoryProduct.models.Product;
import com.categoryProduct.repositories.CategoryRepository;
import com.categoryProduct.repositories.ProductRepository;

@Service

public class CategoryProductService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	// Category CRUD operations

	public Iterable<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}

	public Category getCategoryById(int id) {
		return categoryRepository.findById(id)
				.orElseThrow(
						()->
						{
							throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found");
						}
						);
	}

	public void updateCategory(int id, Category category) {

		this.getCategoryById(id); // Ensure the category exists
		// Implement validation or other business logic if needed
		category.setId(id);
		categoryRepository.save(category);
	}

	//Delete Category
	public void deleteCategory(int id) {
		this.getCategoryById(id);
		categoryRepository.deleteById(id);
	}

	// Product CRUD operations

	public Iterable<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product createProduct(int category_id,Product product) {
		Category category_found = getCategoryById(category_id);
		product.setCategory(category_found);
		Product prod = productRepository.save(product);
		return prod;
	}

	public Product getProductById(int id) {
		return productRepository.findById(id)
				.orElseThrow(
						()->{
							throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found");
						}
						);
	}

	public void updateProduct(int id, Product product) {

		this.getProductById(id); // Ensure the product exists
		product.setId(id);
		productRepository.save(product);
	}

	public void deleteProduct(int id) {
		getProductById(id);
		productRepository.deleteById(id);
	}
}

