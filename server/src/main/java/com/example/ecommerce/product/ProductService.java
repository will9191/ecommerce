package com.example.ecommerce.product;

import com.example.ecommerce.category.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public void save (ProductDto request, Category category) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCategory(category);
        product.setPrice(request.getPrice());
        product.setSizes(request.getSizes());
        product.setImage(request.getImage());
        repository.save(product);
    }

    public List<Product> findAll() {
       return repository.findAll();
    }
}
