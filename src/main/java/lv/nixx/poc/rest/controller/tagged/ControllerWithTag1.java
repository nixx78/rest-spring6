package lv.nixx.poc.rest.controller.tagged;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerWithTag1 {

    @Tag(name="tagged-into-single-group")
    @GetMapping("/tag/endpoint1")
    public String tag1(){
        return "tag1:" + System.currentTimeMillis();
    }
}
