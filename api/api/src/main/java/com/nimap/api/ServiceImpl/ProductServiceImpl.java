package com.nimap.api.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nimap.api.Exception.ProductNotFoundException;
import com.nimap.api.Model.Category;
import com.nimap.api.Model.Product;
import com.nimap.api.Repository.ProductRepository;
import com.nimap.api.Service.CategoryService;
import com.nimap.api.Service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public List<Product> getAllProducts(int page) {
        int pageSize = 10; // Set the desired page size
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        return productRepository.findAll(pageRequest).getContent();
    }

    @Override
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
        product.setCategory(categoryService.getCategoryById(product.getCategory().getId()));
        return product;
    }

    @Override
    public Product createProduct(Product product) {
        Category category = categoryService.getCategoryById(product.getCategory().getId());
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = getProductById(id);
        existingProduct.setName(product.getName());
        existingProduct.setCategory(categoryService.getCategoryById(product.getCategory().getId()));
        return productRepository.save(existingProduct);
    }
    @Override
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}