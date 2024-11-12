package lv.nixx.poc.rest.service;

import lv.nixx.poc.rest.model.behaviour.Action;
import lv.nixx.poc.rest.model.behaviour.DTOWithBehaviour;
import org.springframework.stereotype.Component;

@Component
public class DTOBehaviourService {

    public String process(DTOWithBehaviour request) {
        Action action = request.getAction();
        return action.process(request.getData());
    }

}
