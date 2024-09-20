package lv.nixx.poc.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerWithRecord {

    @PostMapping("/record/test")
    public ResponseAsRecord process(@RequestBody RequestAsRecord request) {
        return new ResponseAsRecord(request.id(), request.request() + ".processed");
    }

    public record RequestAsRecord(Long id, String request) {
    }

    public record ResponseAsRecord(Long id, String value) {

        public String getDescription() {
            return "Response description, id: %s  value: %s".formatted(id, value);
        }

    }

}
