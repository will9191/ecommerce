package com.ecommerce.server;

import com.ecommerce.server.auth.AuthenticationService;
import com.ecommerce.server.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static com.ecommerce.server.user.Role.ADMIN;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service
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
        };
    }
}
