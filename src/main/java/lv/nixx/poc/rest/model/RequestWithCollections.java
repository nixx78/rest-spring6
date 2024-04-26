package lv.nixx.poc.rest.model;

import lv.nixx.poc.rest.validation.collection.CollectionElementRule;

import java.util.Collection;


public record RequestWithCollections(
        @CollectionElementRule(pattern = "^[A-Za-z]+$") Collection<String> collectionWithCharacters,
        @CollectionElementRule(pattern = "^[0-9]+$") Collection<String> collectionWithNumbers
) {

}
