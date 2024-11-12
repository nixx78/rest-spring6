package lv.nixx.poc.rest.controller;

import lv.nixx.poc.rest.model.behaviour.DTOWithBehaviour;
import lv.nixx.poc.rest.service.DTOBehaviourService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DTOWithBehaviourController {

    private final DTOBehaviourService service;

    public DTOWithBehaviourController(DTOBehaviourService service) {
        this.service = service;
    }

    @GetMapping("/test/behaviour")
    public String process(DTOWithBehaviour request) {
        return service.process(request);
    }


}
