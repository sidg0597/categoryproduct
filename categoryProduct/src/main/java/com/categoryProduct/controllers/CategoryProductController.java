package com.categoryProduct.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.categoryProduct.helpers.CategoryProductResponseWrapper;
import com.categoryProduct.models.Category;
import com.categoryProduct.models.Product;
import com.categoryProduct.services.CategoryProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CategoryProductController {
	@Autowired
    private CategoryProductService categoryProductService;

    // Category CRUD operations

    @GetMapping("/categories")
//    	public ResponseEntity<?> getAllCategories(){
//    	Iterable<Category> data = categoryProductService.getAllCategories();
//    	Iterator<Category> all_categories = data.iterator();
//    	if(!all_categories.hasNext()) {
//    		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No categories Found");
//    	}
//    	CategoryProductResponseWrapper cprw = new CategoryProductResponseWrapper();
//    	cprw.setMessage("Record Found");
//    	cprw.setData(all_categories);
//    	return new ResponseEntity<>(cprw,HttpStatus.FOUND);
//    }
    
    public ResponseEntity<Page<Category>> getAllCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categories = categoryProductService.getAllCategories(pageable);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity<?> createCategory(@RequestBody @Valid Category category){
    	Category categoryAdded = categoryProductService.createCategory(category);
    	CategoryProductResponseWrapper cprw = new CategoryProductResponseWrapper();
    	cprw.setMessage("Category added");
    	cprw.setData(categoryAdded);
    	return new ResponseEntity<>(cprw,HttpStatus.CREATED);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable int id) {
        Category category_data = categoryProductService.getCategoryById(id);
        CategoryProductResponseWrapper cprw = new CategoryProductResponseWrapper();
        cprw.setMessage("Category found");
        cprw.setData(category_data);
        return new ResponseEntity<>(cprw,HttpStatus.FOUND);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable int id, @RequestBody Category category) {
        categoryProductService.getCategoryById(id);
        categoryProductService.updateCategory(id, category);
        CategoryProductResponseWrapper cprw = new CategoryProductResponseWrapper();
        cprw.setMessage("Category Updated successfully");
        cprw.setData(categoryProductService.getCategoryById(id));
        return new ResponseEntity<>(cprw,HttpStatus.OK);
    			
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id) {
    	categoryProductService.getCategoryById(id);
    	categoryProductService.deleteCategory(id);
    	return new ResponseEntity<>("Deleted",HttpStatus.NO_CONTENT);
    }

    // Product CRUD operations

    @GetMapping("/products")
//	public ResponseEntity<?> getAllProducts(){
//	Iterable<Product> data = categoryProductService.getAllProducts();
//	Iterator<Product> all_products = data.iterator();
//	if(!all_products.hasNext()) {
//		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No Products Found");
//	}
//	CategoryProductResponseWrapper cprw = new CategoryProductResponseWrapper();
//	cprw.setMessage("Record Found");
//	cprw.setData(all_products);
//	return new ResponseEntity<>(cprw,HttpStatus.FOUND);
//}

    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = categoryProductService.getAllProducts(pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    
@PostMapping("/products/{id}")
public ResponseEntity<?> createProduct(@PathVariable int id, @RequestBody @Valid  Product product){
	Product productAdded = categoryProductService.createProduct(id,product);
	CategoryProductResponseWrapper cprw = new CategoryProductResponseWrapper();
	cprw.setMessage("Product added");
	cprw.setData(productAdded);
	return new ResponseEntity<>(cprw,HttpStatus.OK);
}

@GetMapping("/products/{id}")
public ResponseEntity<?> getProductById(@PathVariable int id) {
    Product product_data = categoryProductService.getProductById(id);
    CategoryProductResponseWrapper cprw = new CategoryProductResponseWrapper();
    cprw.setMessage("Product found");
    cprw.setData(product_data);
    return new ResponseEntity<>(cprw,HttpStatus.FOUND);
}

@PutMapping("/products/{id}")
public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody Product product) {
    categoryProductService.getProductById(id);
    categoryProductService.updateProduct(id, product);
    CategoryProductResponseWrapper cprw = new CategoryProductResponseWrapper();
    cprw.setMessage("Product Updated successfully");
    cprw.setData(categoryProductService.getProductById(id));
    return new ResponseEntity<>(cprw,HttpStatus.OK);
			
}

@DeleteMapping("/products/{id}")
public ResponseEntity<?> deleteProduct(@PathVariable int id) {
	categoryProductService.getProductById(id);
	categoryProductService.deleteProduct(id);
	return new ResponseEntity<>("Deleted",HttpStatus.NO_CONTENT);
}
    }

