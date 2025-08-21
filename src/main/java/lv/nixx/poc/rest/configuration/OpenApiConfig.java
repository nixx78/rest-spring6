package lv.nixx.poc.rest.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi groupApi() {
        return GroupedOpenApi.builder()
                .group("group")
                .packagesToScan("lv.nixx.poc.rest.controller.group")
                .build();
    }

    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder()
                .group("all")
                .packagesToScan("lv.nixx.poc.rest.controller")
                .packagesToExclude("lv.nixx.poc.rest.controller.group")
                .build();
    }


}

