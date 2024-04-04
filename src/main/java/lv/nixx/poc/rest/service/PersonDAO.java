package lv.nixx.poc.rest.service;

import lv.nixx.poc.rest.PersonNotFoundException;
import lv.nixx.poc.rest.model.NewPersonRequest;
import lv.nixx.poc.rest.model.PersonDTO;
import lv.nixx.poc.rest.model.UpdatePersonRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class PersonDAO {

    private static final AtomicInteger id = new AtomicInteger();
    private final Map<Long, PersonDTO> personMap = new ConcurrentHashMap<>();
    private final Map<UUID, Integer[]> removeBatch = new HashMap<>();

    public PersonDAO() {

        Map<Long, PersonDTO> v = Stream.of(
                new PersonDTO(1L, "name1", "surname1", LocalDate.parse("1978-10-05")),
                new PersonDTO(2L, "name2", "surname2", LocalDate.parse("1980-10-07")),
                new PersonDTO(3L, "name3", "surname3", LocalDate.parse("1980-05-10")),
                new PersonDTO(4L, "name4", "surname4", LocalDate.parse("1978-12-06")),
                new PersonDTO(5L, "name5", "surname5", LocalDate.parse("1980-05-15"))
        ).collect(Collectors.toMap(PersonDTO::getId, Function.identity()));

        this.personMap.putAll(v);
    }

    public PersonDTO addPerson(NewPersonRequest request) {
        PersonDTO createdPerson = new PersonDTO(id.incrementAndGet(), request.getName(), request.getSurname(), request.getDateOfBirth());
        personMap.put(createdPerson.getId(), createdPerson);

        return createdPerson;
    }

    public PersonDTO getById(Long id) {
        if (personMap.containsKey(id)) {
            return personMap.get(id);
        }
        throw new PersonNotFoundException("Person with id [" + id + "] not found");
    }

    public PersonDTO delete(Long id) {
        if (personMap.containsKey(id)) {
            return personMap.remove(id);
        }
        throw new PersonNotFoundException("Person with id [" + id + "] not found");
    }

    public PersonDTO update(UpdatePersonRequest request) {
        PersonDTO person = new PersonDTO(id.incrementAndGet(), request.getName(), request.getSurname(), request.getDateOfBirth());

        personMap.put(person.getId(), person);

        return person;
    }

    public Collection<PersonDTO> getAllPersons() {
        return personMap.values();
    }

    public UUID addToDeleteBatch(Integer[] ids) {
        UUID batchId = UUID.randomUUID();
        removeBatch.put(batchId, ids);
        return batchId;
    }

    public boolean isBatchExists(UUID batchId) {
        return removeBatch.containsKey(batchId);
    }

    public Integer[] deletePersonBatch(UUID batchId) {
        return removeBatch.remove(batchId);
    }


}
