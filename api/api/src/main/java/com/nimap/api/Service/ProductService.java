package com.nimap.api.Service;

import java.util.List;

import com.nimap.api.Model.Product;

public interface ProductService {
    List<Product> getAllProducts(int page);
    Product getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);

		
	}