package lv.nixx.poc.rest.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.util.stream.Collectors;

/*
Конфигурация ObjectMapper который будет использоваться Spring для сериализации/десериализации JSON -> Java Object
 */

@Configuration
public class JacksonConfig {

    private static final Logger log = LoggerFactory.getLogger(JacksonConfig.class);

    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        SimpleModule module = new SimpleModule(TrimStringDeserializer.class.getName());
        module.addDeserializer(String.class, new TrimStringDeserializer());

        ObjectMapper objectMapper = builder.build();
        objectMapper.registerModule(module);

        if (log.isInfoEnabled()) {
            String m = objectMapper.getRegisteredModuleIds().stream()
                    .map(Object::toString)
                    .collect(Collectors.joining("\n\t\t"));

            log.info("ObjectMapper modules:\n\t\t{}", m);
        }

        return objectMapper;
    }

    static class TrimStringDeserializer extends JsonDeserializer<String> {

        // Удаляет пробелы в начале и конце строки, если строка становится пустой, то заменяет ее на null;
        @Override
        public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            String value = jsonParser.getValueAsString();
            String s = (value != null) ? value.trim() : null;
            return StringUtils.isEmpty(s) ? null : s;
        }
    }


}
