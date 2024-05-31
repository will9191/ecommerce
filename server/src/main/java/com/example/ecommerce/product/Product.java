package com.example.ecommerce.product;

import com.example.ecommerce.category.Category;
import com.example.ecommerce.size.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
@Transactional
public class Product {
    @Id
    @GeneratedValue
    private Long productId;
    private String name;
    private String image;
    private String description;
    private double price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;
    @Embedded
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Size> sizes;
}
