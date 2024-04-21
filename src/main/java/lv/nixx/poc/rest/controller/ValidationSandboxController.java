package lv.nixx.poc.rest.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lv.nixx.poc.rest.model.RequestWithCollection;
import lv.nixx.poc.rest.validation.collection.CollectionElementRule;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@Validated
public class ValidationSandboxController {

    @PostMapping("/poc/validateObject")
    public String process(@Valid @RequestBody RequestWithCollection request) {
        return System.currentTimeMillis() + ": processed";
    }

    @PostMapping("/poc/validateParameter")
    @Parameter(example = "1,2,3")
    public String processCollection(@RequestParam("collection") @CollectionElementRule(pattern = "^[0-9]+$") Collection<String> collection) {
        return System.currentTimeMillis() + ": processed";
    }

}
