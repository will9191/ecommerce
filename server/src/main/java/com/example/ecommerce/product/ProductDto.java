package com.example.ecommerce.product;

import com.example.ecommerce.size.Size;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class ProductDto {
    private Long productId;
    private String name;
    private String image;
    private String description;
    private double price;
    private Long categoryId;
    private Set<Size> sizes;
}
