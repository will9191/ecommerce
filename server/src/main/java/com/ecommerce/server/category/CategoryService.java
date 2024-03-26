package com.ecommerce.server.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public Category register(CategoryRequest request) {
        return Category.builder().name(request.getName()).build();
    }

    public List<Category> findAll() {
        return repository.findAll();
    }
}
