package com.example.ecommerce.size;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Size {
    private int idaa;
    private SizeType name;
    private Integer quantity;
}
