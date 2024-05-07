package com.example.ecommerce.category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {
    private Long categoryId;
    private String name;
    private String referenceName;
    private String description;
    private String imageUrl;
}
