package com.example.ecommerce;

import com.example.ecommerce.auth.AuthenticationService;
import com.example.ecommerce.auth.RegisterRequest;
import com.example.ecommerce.category.Category;
import com.example.ecommerce.category.CategoryDto;
import com.example.ecommerce.category.CategoryRepository;
import com.example.ecommerce.category.CategoryService;
import com.example.ecommerce.product.ProductController;
import com.example.ecommerce.product.ProductDto;
import com.example.ecommerce.product.ProductService;
import com.example.ecommerce.size.Size;
import com.example.ecommerce.size.SizeType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.example.ecommerce.user.Role.ADMIN;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service,
            CategoryService categoryService,
            ProductController productController,
            CategoryRepository categoryRepository
    ) {
        return args -> {
            var admin = RegisterRequest.builder()
                    .firstName("Willian")
                    .lastName("Pereira")
                    .email("admin@gmail.com")
                    .password("password")
                    .role(ADMIN)
                    .build();
            System.out.println("Admin token: " + service.register(admin).getAccessToken());

            var category = CategoryDto.builder()
                    .name("Drop X")
                    .referenceName("drop-x")
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet mattis vulputate enim nulla aliquet porttitor. Accumsan.")
                    .imageUrl("https://pbs.twimg.com/media/EYHAwfjXkAA0MXb.jpg:large")
                    .build();
            categoryService.save(category);


            var size1 = new Size(SizeType.P, 100);
            var size2 = new Size(SizeType.M, 200);
            var size3 = new Size(SizeType.G, 300);
            var size4 = new Size(SizeType.GG, 400);
            var size5 = new Size(SizeType.XG, 500);
            Set<Size> sizes = new HashSet<>(List.of(new Size[]{size1, size2, size3, size4, size5}));

            var product1 = ProductDto.builder()
                    .name("Caktus Jack")
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet mattis vulputate enim nulla aliquet porttitor. Accumsan.")
                    .image("https://www.cretineoficial.com/cdn/shop/products/image_56c5b10f-a2e5-4e38-ad00-0e463f80ffe3_1200x1200.png?v=1664425870")
                    .price(299)
                    .categoryId(1L)
                    .sizes(sizes)
                    .build();
            productController.save(product1);


            var product2 = ProductDto.builder()
                    .name("Kendrick Lamar")
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet mattis vulputate enim nulla aliquet porttitor. Accumsan.")
                    .image("https://agedarchive.com/cdn/shop/files/KENDRICK-OFFWHITE_1024x1024@2x.png?v=1692202127")
                    .price(299)
                    .categoryId(1L)
                    .sizes(sizes)
                    .build();
            productController.save(product2);


            var product3 = ProductDto.builder()
                    .name("21 Savage")
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet mattis vulputate enim nulla aliquet porttitor. Accumsan.")
                    .image("https://www.styltrap.com/cdn/shop/products/S25c4db6479f34846b5d564423dcb4d20Y.jpg?v=1676481709")
                    .price(299)
                    .categoryId(1L)
                    .sizes(sizes)
                    .build();
            productController.save(product3);
        };
    }
}
