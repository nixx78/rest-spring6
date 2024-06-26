package lv.nixx.poc.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class RestSpring6Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RestSpring6Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RestSpring6Application.class);
    }
}
