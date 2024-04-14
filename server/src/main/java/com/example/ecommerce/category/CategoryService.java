package com.example.ecommerce.category;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public void save(CategoryDto request) {
        var category  = Category.builder().name(request.getName()).description(request.getDescription()).imageUrl(request.getImageUrl()).build();
        repository.save(category);
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public void update(CategoryDto request) {
        Optional<Category> optionalCategory = repository.findById(request.getCategoryId());
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setCategoryId(request.getCategoryId());
            category.setName(request.getName());
            category.setDescription(request.getDescription());
            category.setImageUrl(request.getImageUrl());
        } else {
            throw new EntityNotFoundException();
        }
    }


}
