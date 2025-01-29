package lv.nixx.poc.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.Arrays;

@SpringBootApplication
public class RestSpring6Application extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(RestSpring6Application.class);

    public static void main(String[] args) {

        log.info("Application parameters in main class: {}", Arrays.toString(args));

        SpringApplication.run(RestSpring6Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        // This configuration will work only in case of we run our application on Tomcat server
        return application.sources(RestSpring6Application.class)
                .profiles("tomcat");
    }

}