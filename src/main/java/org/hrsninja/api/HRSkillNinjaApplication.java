package org.hrsninja.api;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HRSkillNinjaApplication {
    public static void main(String[] args) {
        SpringApplication.run(HRSkillNinjaApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("HR Skill Ninja API")
                        .version("1.0")
                        .description("A lightweight candidate-tracking microservice for internal HR department use")
                        .contact(new Contact()
                                .name("HR Department")
                                .email("hr@company.com"))
                        .license(new License()
                                .name("Internal Use Only")
                                .url("https://company.com/internal")));
    }
}
