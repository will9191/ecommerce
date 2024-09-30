package com.example.ecommerce;

import com.example.ecommerce.auth.AuthenticationService;
import com.example.ecommerce.auth.RegisterRequest;
import com.example.ecommerce.category.CategoryDto;
import com.example.ecommerce.category.CategoryService;
import com.example.ecommerce.product.ProductController;
import com.example.ecommerce.product.ProductDto;
import com.example.ecommerce.size.Size;
import com.example.ecommerce.size.SizeType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
            ProductController productController

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

            var category1 = CategoryDto.builder()
                    .name("Drop X")
                    .referenceName("drop-x")
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet mattis vulputate enim nulla aliquet porttitor. Accumsan.")
                    .imageUrl("https://pbs.twimg.com/media/EYHAwfjXkAA0MXb.jpg:large")
                    .build();
            categoryService.save(category1);

            var category2 = CategoryDto.builder()
                    .name("Drop Y")
                    .referenceName("drop-y")
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet mattis vulputate enim nulla aliquet porttitor. Accumsan.")
                    .imageUrl("https://pbs.twimg.com/media/EYHAwfjXkAA0MXb.jpg:large")
                    .build();
            categoryService.save(category2);

            var category3 = CategoryDto.builder()
                    .name("Drop Z")
                    .referenceName("drop-z")
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet mattis vulputate enim nulla aliquet porttitor. Accumsan.")
                    .imageUrl("https://pbs.twimg.com/media/EYHAwfjXkAA0MXb.jpg:large")
                    .build();
            categoryService.save(category3);


            var size1 = new Size(SizeType.P, 1);
            var size2 = new Size(SizeType.M, 1);
            var size3 = new Size(SizeType.G, 1);
            var size4 = new Size(SizeType.GG, 1);
            var size5 = new Size(SizeType.XG, 1);

            List<Size> sizes = new ArrayList<>(List.of(new Size[]{size1, size2, size3, size4, size5}));

            var product1 = ProductDto.builder()
                    .name("Caktus Jack")
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet mattis vulputate enim nulla aliquet porttitor. Accumsan.")
                    .image("https://raw.githubusercontent.com/will9191/files/refs/heads/main/travis.png")
                    .price(299)
                    .categoryId(1L)
                    .sizes(sizes)
                    .build();
            productController.save(product1);


            var product2 = ProductDto.builder()
                    .name("Kendrick Lamar")
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet mattis vulputate enim nulla aliquet porttitor. Accumsan.")
                    .image("https://raw.githubusercontent.com/will9191/files/refs/heads/main/kendrick.png")
                    .price(299)
                    .categoryId(1L)
                    .sizes(sizes)
                    .build();
            productController.save(product2);


            var product3 = ProductDto.builder()
                    .name("21 Savage")
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet mattis vulputate enim nulla aliquet porttitor. Accumsan.")
                    .image("https://raw.githubusercontent.com/will9191/files/refs/heads/main/21.png")
                    .price(299)
                    .categoryId(1L)
                    .sizes(sizes)
                    .build();
            productController.save(product3);

            var product4 = ProductDto.builder()
                    .name("Tupac")
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet mattis vulputate enim nulla aliquet porttitor. Accumsan.")
                    .image("https://raw.githubusercontent.com/will9191/files/refs/heads/main/tupac.png")
                    .price(299)
                    .categoryId(2L)
                    .sizes(sizes)
                    .build();
            productController.save(product4);

            var product5 = ProductDto.builder()
                    .name("Kobe Bryant")
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet mattis vulputate enim nulla aliquet porttitor. Accumsan.")
                    .image("https://raw.githubusercontent.com/will9191/files/refs/heads/main/kobe.jpg")
                    .price(299)
                    .categoryId(2L)
                    .sizes(sizes)
                    .build();
            productController.save(product5);

            var product6 = ProductDto.builder()
                    .name("Rodman")
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet mattis vulputate enim nulla aliquet porttitor. Accumsan.")
                    .image("https://raw.githubusercontent.com/will9191/files/refs/heads/main/rodman.png")
                    .price(299)
                    .categoryId(2L)
                    .sizes(sizes)
                    .build();
            productController.save(product6);

            var product7 = ProductDto.builder()
                    .name("Michael Jordan")
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet mattis vulputate enim nulla aliquet porttitor. Accumsan.")
                    .image("https://raw.githubusercontent.com/will9191/files/refs/heads/main/mj.png")
                    .price(299)
                    .categoryId(3L)
                    .sizes(sizes)
                    .build();
            productController.save(product7);

            var product8 = ProductDto.builder()
                    .name("Post Malone")
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet mattis vulputate enim nulla aliquet porttitor. Accumsan.")
                    .image("https://raw.githubusercontent.com/will9191/files/refs/heads/main/postmalone.png")
                    .price(299)
                    .categoryId(3L)
                    .sizes(sizes)
                    .build();
            productController.save(product8);

            var product9 = ProductDto.builder()
                    .name("Kurt Kobain")
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet mattis vulputate enim nulla aliquet porttitor. Accumsan.")
                    .image("https://raw.githubusercontent.com/will9191/files/refs/heads/main/kurt.png")
                    .price(299)
                    .categoryId(3L)
                    .sizes(sizes)
                    .build();
            productController.save(product9);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        };
    }
}
