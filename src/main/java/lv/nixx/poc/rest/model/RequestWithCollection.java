package lv.nixx.poc.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lv.nixx.poc.rest.validation.collection.CollectionElement;

import java.util.Collection;

@Getter
public class RequestWithCollection {

    @CollectionElement(pattern = "^[A-Z][A-Za-z]+$")
    private final Collection<String> collectionWithCharacters;

    @CollectionElement(pattern = "^[0-9]+$")
    private final Collection<String> collectionWithNumbers;

    @JsonCreator
    public RequestWithCollection(
            @JsonProperty("collectionWithCharacters") Collection<String> collectionWithCharacters,
            @JsonProperty("collectionWithNumbers") Collection<String> collectionWithNumbers)
    {
        this.collectionWithCharacters = collectionWithCharacters;
        this.collectionWithNumbers = collectionWithNumbers;
    }
}
