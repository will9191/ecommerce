package com.example.ecommerce.category;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @PostMapping
    public ResponseEntity<?> createNew(@RequestBody @Valid CategoryDto request) {
        service.save(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{referenceName}")
    public ResponseEntity<Optional<Category>> getProductsByCategory(@PathVariable String referenceName) {
        return ResponseEntity.ok(service.findByReferenceName(referenceName));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid CategoryDto request) {
        service.update(request);
        return ResponseEntity.ok(request);
    }
}
