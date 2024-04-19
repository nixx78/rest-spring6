package lv.nixx.poc.rest.controller;

import jakarta.validation.Valid;
import lv.nixx.poc.rest.model.RequestWithCollection;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class ValidationSandboxController {

    @PostMapping("/poc/validation")
    public String process(@Valid @RequestBody RequestWithCollection request) {
        return System.currentTimeMillis() + ": processed";
    }

}
