package lv.nixx.poc.rest.controller.group;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerInGroup2 {

    @GetMapping("/group/endpoint2")
    public String endpoint2(){
        return "endpoint2:" + System.currentTimeMillis();
    }
}
