package lv.nixx.poc.rest.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

@Configuration
public class CustomControllerConfiguration {

    private static final Logger log = LoggerFactory.getLogger(CustomControllerConfiguration.class);

    @Bean
    public Object myHelloController(RequestMappingHandlerMapping handlerMapping) throws NoSuchMethodException {

        Object controller = new Object() {
            // Код, который будет вызыватся при REST запросе
            public ResponseEntity<String> sayHello() {
                return ResponseEntity.ok("Hello, World!");
            }
        };

        // В данном случае, использование рефлексии не оказывает особого внимания на производительность,
        // используется только при регистрации
        Method method = controller.getClass().getMethod("sayHello");

        RequestMappingInfo mappingInfo = RequestMappingInfo
                .paths("/api/hello")            // Контроллер будет доступен по этому пути
                .methods(RequestMethod.GET)
                .build();


        log.info("Custom controller available on path: {}", mappingInfo);

        handlerMapping.registerMapping(mappingInfo, controller, method);

        return controller;
    }
}
