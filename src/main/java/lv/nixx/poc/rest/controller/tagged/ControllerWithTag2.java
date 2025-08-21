package lv.nixx.poc.rest.controller.tagged;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerWithTag2 {

    @Tag(name="tagged-into-single-group")
    @GetMapping("/tag/endpoint2")
    public String tag2(){
        return "tag2:" + System.currentTimeMillis();
    }
}
