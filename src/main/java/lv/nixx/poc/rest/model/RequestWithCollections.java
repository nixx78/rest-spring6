package lv.nixx.poc.rest.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lv.nixx.poc.rest.validation.collection.CollectionElementRule;

import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RequestWithCollections extends RequestWithDateRange {

    @CollectionElementRule(pattern = "^[A-Za-z]+$")
    Collection<String> collectionWithCharacters;

    @CollectionElementRule(pattern = "^[0-9]+$")
    Collection<String> collectionWithNumbers;

}