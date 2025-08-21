package lv.nixx.poc.rest.controller.group;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerInGroup1 {

    @GetMapping("/group/endpoint1")
    public String endpoint1(){
        return "endpoint1:" + System.currentTimeMillis();
    }
}
