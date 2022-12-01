package com.skyfall.tutorial.springbootapplication;

import com.skyfall.tutorial.springbootapplication.dto.FileUploadDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileUploadDTO.class
})
@OpenAPIDefinition(info = @Info(title = "Department API", version = "1.0", description = "Testing openapi"))
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
