package com.example.ecommerce.product;

import com.example.ecommerce.category.Category;
import com.example.ecommerce.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    private final CategoryRepository repository;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ProductDto request) throws Exception {
        Optional<Category> optionalCategory = repository.findById(request.getCategoryId());
        if (optionalCategory.isEmpty()) {
            throw new Exception();
        }
        service.save(request, optionalCategory.get());
        return ResponseEntity.ok("Ok");
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
