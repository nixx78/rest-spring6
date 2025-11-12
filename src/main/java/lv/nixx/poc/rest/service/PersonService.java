package lv.nixx.poc.rest.service;

import lv.nixx.poc.rest.exception.PersonNotFoundException;
import lv.nixx.poc.rest.model.NewPersonRequest;
import lv.nixx.poc.rest.model.PersonDTO;
import lv.nixx.poc.rest.model.UpdatePersonRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class PersonService {

    private static final AtomicLong ID_GENERATOR = new AtomicLong();
    private final Map<Long, PersonDTO> personMap = new ConcurrentHashMap<>();

    private static final Map<PersonDTO.FieldsToPatch, BiConsumer<PersonDTO, Object>> PATCH_ACTIONS = Map.of(
            PersonDTO.FieldsToPatch.name, (p, v) -> p.setName((String) v),
            PersonDTO.FieldsToPatch.surname, (p, v) -> p.setSurname((String) v),
            PersonDTO.FieldsToPatch.dateOfBirth, (p, v) -> p.setDateOfBirth(LocalDate.parse((CharSequence) v))
    );

    public PersonService() {

        Map<Long, PersonDTO> v = Stream.of(
                new PersonDTO(ID_GENERATOR.incrementAndGet(), "name1", "surname1", LocalDate.parse("1978-10-05")),
                new PersonDTO(ID_GENERATOR.incrementAndGet(), "name2", "surname2", LocalDate.parse("1980-10-07")),
                new PersonDTO(ID_GENERATOR.incrementAndGet(), "name3", "surname3", LocalDate.parse("1980-05-10")),
                new PersonDTO(ID_GENERATOR.incrementAndGet(), "name4", "surname4", LocalDate.parse("1978-12-06")),
                new PersonDTO(ID_GENERATOR.incrementAndGet(), "name5", "surname5", LocalDate.parse("1980-05-15"))
        ).collect(Collectors.toMap(PersonDTO::getId, Function.identity()));

        this.personMap.putAll(v);
    }

    public PersonDTO addPerson(NewPersonRequest request) {
        PersonDTO createdPerson = new PersonDTO(ID_GENERATOR.incrementAndGet(), request.getName(), request.getSurname(), request.getDateOfBirth());
        personMap.put(createdPerson.getId(), createdPerson);

        return createdPerson;
    }

    public Collection<PersonDTO> addBulkPerson(Collection<NewPersonRequest> request) {
        return request.stream()
                .map(this::addPerson)
                .toList();
    }

    public PersonDTO getById(Long id) {
        if (personMap.containsKey(id)) {
            return personMap.get(id);
        }
        throw new PersonNotFoundException(id);
    }

    public void delete(Long id) {
        if (personMap.containsKey(id)) {
            personMap.remove(id);
        } else {
            throw new PersonNotFoundException(id);
        }
    }

    public PersonDTO update(UpdatePersonRequest updateRequest) {
        Long personId = updateRequest.getId();
        if (!personMap.containsKey(personId)) {
            throw new PersonNotFoundException(personId);
        }
        return personMap.computeIfPresent(personId, (k, v) -> new PersonDTO(personId, updateRequest.getName(), updateRequest.getSurname(), updateRequest.getDateOfBirth()));
    }

    public Collection<PersonDTO> getAllPersons() {
        return personMap.values();
    }

    public PersonDTO patchPerson(Long personId, Map<PersonDTO.FieldsToPatch, Object> fieldsToPatch) {

        PersonDTO originalPerson = Optional.ofNullable(personMap.get(personId))
                .orElseThrow(() -> new PersonNotFoundException(personId));

        PersonDTO person = originalPerson.toBuilder().build();

        fieldsToPatch.forEach((field, value) -> {
            BiConsumer<PersonDTO, Object> action = PATCH_ACTIONS.get(field);
            if (action == null) {
                throw new IllegalArgumentException("Unsupported field: " + field);
            } else {
                action.accept(person, value);
            }
        });

        personMap.put(personId, person);

        return person;
    }

}
