package pl.ds.waluty.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public OpenAPI customOpenAPI(@Value("${app.description}") String appDescription,
                                 @Value("${app.version}") String appVersion) {

        return new OpenAPI().info(new Info().title("Prototym mikroserwisu Zakup-Walut w NBP API").version(appVersion)
                .description(appDescription).termsOfService("http://swagger.io/terms/")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")));

    }
}
