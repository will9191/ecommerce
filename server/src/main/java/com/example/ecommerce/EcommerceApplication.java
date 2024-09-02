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
                    .coins(500)
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


            var size1 = new Size(1,SizeType.P, 50);
            var size2 = new Size(2,SizeType.M, 50);
            var size3 = new Size(3,SizeType.G, 50);
            var size4 = new Size(4,SizeType.GG, 50);
            var size5 = new Size(5,SizeType.XG, 50);

            List<Size> sizes = new ArrayList<>(List.of(new Size[]{size1, size2, size3, size4, size5}));

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

            var product4 = ProductDto.builder()
                    .name("Tupac")
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet mattis vulputate enim nulla aliquet porttitor. Accumsan.")
                    .image("https://ae01.alicdn.com/kf/H82ac43e81b5149239d21a19ed7d825c9t.jpg?width=1500&height=1500&hash=3000")
                    .price(299)
                    .categoryId(2L)
                    .sizes(sizes)
                    .build();
            productController.save(product4);

            var product5 = ProductDto.builder()
                    .name("Kobe Bryant")
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet mattis vulputate enim nulla aliquet porttitor. Accumsan.")
                    .image("https://www.cretineoficial.com/cdn/shop/files/421C820D-76F9-452D-B971-2AA09C64C866_1024x1024@2x.jpg?v=1695848725")
                    .price(299)
                    .categoryId(2L)
                    .sizes(sizes)
                    .build();
            productController.save(product5);

            var product6 = ProductDto.builder()
                    .name("Rodman")
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet mattis vulputate enim nulla aliquet porttitor. Accumsan.")
                    .image("https://www.cretineoficial.com/cdn/shop/products/S15ccfecef1ef405d832bf1bdbb86d8e4T_1024x1024@2x.jpg?v=1686082152")
                    .price(299)
                    .categoryId(2L)
                    .sizes(sizes)
                    .build();
            productController.save(product6);

            var product7 = ProductDto.builder()
                    .name("Michael Jordan")
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet mattis vulputate enim nulla aliquet porttitor. Accumsan.")
                    .image("https://www.cretineoficial.com/cdn/shop/files/IMG-9290_1024x1024@2x.jpg?v=1702933282")
                    .price(299)
                    .categoryId(3L)
                    .sizes(sizes)
                    .build();
            productController.save(product7);

            var product8 = ProductDto.builder()
                    .name("Post Malone")
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet mattis vulputate enim nulla aliquet porttitor. Accumsan.")
                    .image("https://www.cretineoficial.com/cdn/shop/files/IMG-9324_1024x1024@2x.jpg?v=1702966278")
                    .price(299)
                    .categoryId(3L)
                    .sizes(sizes)
                    .build();
            productController.save(product8);

            var product9 = ProductDto.builder()
                    .name("Kurt Kobain")
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet mattis vulputate enim nulla aliquet porttitor. Accumsan.")
                    .image("https://www.cretineoficial.com/cdn/shop/files/IMG-9285_1024x1024@2x.jpg?v=1702932497")
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
