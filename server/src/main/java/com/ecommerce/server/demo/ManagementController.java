package com.ecommerce.server.demo;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/management")
@Tag(name = "Management")
public class ManagementController {

    @Operation(
            description = "Get endpoint for management",
            summary = "Summary for management endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            }
    )
    @GetMapping
    public String get(){
        return "GET:: management controller";
    }
    @PostMapping
    public String post(){
        return "POST:: management controller";
    }
    @PutMapping
    public String put(){
        return "PUT:: management controller";
    }
    @DeleteMapping
    public String delete(){
        return "DELETE:: management controller";
    }
}
