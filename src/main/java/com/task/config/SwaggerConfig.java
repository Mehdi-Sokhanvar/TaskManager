package com.task.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    public OpenAPI customApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Task Manager")
                        .version("1.0")
                        .description("Task Manager"));
    }
}
