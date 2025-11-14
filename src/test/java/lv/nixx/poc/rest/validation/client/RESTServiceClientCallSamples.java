package lv.nixx.poc.rest.validation.client;

import lv.nixx.poc.rest.model.PersonDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Disabled
class RESTServiceClientCallSamples {

    private final RestClient client = RestClient.create();
    private final WebClient webClient = WebClient.create();

    @Test
    void doGetSimple() {
        ResponseEntity<PersonDTO> response = client.get()
                .uri("http://localhost:8080/rest-spring/person/1")
                .retrieve()
                .toEntity(PersonDTO.class);

        System.out.println("Response using 'RestClient': " +  response);
    }

    @Test
    void doGetReactive() {
        Mono<PersonDTO> mono = webClient.get()
                .uri("http://localhost:8080/rest-spring/person/1")
                .retrieve()
                .bodyToMono(PersonDTO.class);

        PersonDTO response = mono.block(); // only for non-reactive application
        System.out.println("Response using 'WebClient': " +  response);
    }

}
