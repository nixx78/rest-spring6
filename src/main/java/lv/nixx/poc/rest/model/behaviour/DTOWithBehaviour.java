package lv.nixx.poc.rest.model.behaviour;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class DTOWithBehaviour {

    private final String data;
    private final Action action;

    @JsonCreator
    public DTOWithBehaviour(String data, Action action) {
        this.data = data;
        this.action = action;
    }

}

