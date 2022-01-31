package pl.ds.waluty.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class LoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        response.getHeaders().add("LOGOWANIE", "TRUE");
        logResponse(response);

        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) throws IOException {

        if (log.isInfoEnabled()) {
            log.info("===log request start===");
            log.info("URI: {}", request.getURI());
            log.info("Method: {}", request.getMethod());
            log.info("Headers: {}", request.getHeaders());
            log.info("Request body: {}", new String(body, StandardCharsets.UTF_8));
            log.info("===log request end===");
        }
    }

    private void logResponse(ClientHttpResponse response) throws IOException {

        if (log.isInfoEnabled()) {
            log.info("===log response start===");
            log.info("Status code: {}", response.getStatusCode());
            log.info("Status text: {}", response.getStatusText());
            log.info("Headers: {}", response.getHeaders());
            log.info("Response body: {}", StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
            log.info("===log response end===");
        }
    }
}