package com.github.gdrouet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@SpringBootApplication
@RestController
public class Issue117 {

    public static void main(String[] args) {
        SpringApplication.run(Issue117.class, args);
    }

    @GetMapping
    public ResponseEntity<String> proxy(final ProxyExchange<String> proxy, final HttpServletRequest request)
            throws Exception {
        final String url = "http://localhost:3000";
        final HttpHeaders headers = extractHeaders(request);
        return proxy
                //.headers(headers)
                .uri(url)
                .get();
    }

    private HttpHeaders extractHeaders(final HttpServletRequest request) {
        final HttpHeaders headers = new HttpHeaders();
        final Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            final String headerName = headerNames.nextElement();
            final Enumeration<String> values = request.getHeaders(headerName);
            final List<String> headerValues = new ArrayList<>();

            while (values.hasMoreElements()) {
                headerValues.add(values.nextElement());
            }

            headers.addAll(headerName, headerValues);
        }

        return headers;
    }
}
