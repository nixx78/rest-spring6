package lv.nixx.poc.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lv.nixx.poc.rest.validation.collection.CollectionElementRule;

import java.util.Collection;


public record RequestWithCollection(
        @CollectionElementRule(pattern = "^[A-Za-z]+$") Collection<String> collectionWithCharacters,
        @CollectionElementRule(pattern = "^[0-9]+$") Collection<String> collectionWithNumbers) {

    @JsonCreator
    public RequestWithCollection(
            @JsonProperty("collectionWithCharacters") Collection<String> collectionWithCharacters,
            @JsonProperty("collectionWithNumbers") Collection<String> collectionWithNumbers)
    {
        this.collectionWithCharacters = collectionWithCharacters;
        this.collectionWithNumbers = collectionWithNumbers;
    }
}
