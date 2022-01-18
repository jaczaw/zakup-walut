package pl.ds.waluty.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import pl.ds.waluty.utils.LoggingInterceptor;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

/*
jeśli chcemy, aby nasz interceptor działał jako rejestrator żądań/odpowiedzi,
 musimy go odczytać dwukrotnie – pierwszy raz przez interceptora, a drugi raz przez klienta.
https://www.baeldung.com/spring-rest-template-interceptor#:~:text=RestTemplate%20object%20is%20initialized%20using%C2%A0BufferingClientHttpRequestFactory
*/
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(
                new BufferingClientHttpRequestFactory( //aby włączyć buforowanie strumienia żądania/odpowiedzi:
                        new SimpleClientHttpRequestFactory()
                )
        );

        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(new LoggingInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }


    @Bean
    public OpenAPI customOpenAPI(@Value("${app.description}") String appDescription,
                                 @Value("${app.version}") String appVersion) {

        return new OpenAPI()
                .info(new Info()
                        .title("Prototym mikroserwisu Zakup-Walut w NBP API")
                        .version(appVersion)
                .description(appDescription)
                        .termsOfService("http://swagger.io/terms/")
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://springdoc.org")));

    }
}
