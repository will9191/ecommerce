package com.example.ecommerce.category;

import lombok.Data;

@Data
public class CategoryDto {
    private Long categoryId;
    private String name;
    private String description;
    private String imageUrl;
}
