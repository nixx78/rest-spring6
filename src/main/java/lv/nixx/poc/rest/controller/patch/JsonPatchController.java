package lv.nixx.poc.rest.controller.patch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sample/patch/user")
public class JsonPatchController {

    private final ObjectMapper mapper = new ObjectMapper();
    private final Map<Long, UserDTO> users = new HashMap<>();

    public JsonPatchController() {

        users.putAll(Map.of(
                1L, new UserDTO()
                        .setId(1L)
                        .setName("Alice")
                        .setEmail("alice@example.com")
                        .setAge(30),
                2L, new UserDTO()
                        .setId(1L)
                        .setName("Alice")
                        .setEmail("alice@example.com")
                        .setAge(30)
        ));

    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return users.get(id);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = """
                    Supported operations:
                    - replace — change existing field;
                    - add — add new field;
                    - remove — remove field (set null);
                    """,
            required = true,
            content = @Content(
                    mediaType = "application/json-patch+json",
                    schema = @Schema(
                            type = "object",
                            example = """
                                      [{ "op": "replace", "path": "/name", "value": "John" },{ "op": "remove", "path": "/age" },{ "op": "add", "path": "/email", "value":"e@mail.me" }]"""
                    )
            )
    )
    public UserDTO patchUserData(@PathVariable Long id, @RequestBody JsonPatch patch) {
        return applyPatchToUser(id, patch);
    }

    public UserDTO applyPatchToUser(Long id, JsonPatch patch) {
        UserDTO user = findUserById(id);

        try {
            JsonNode userNode = mapper.convertValue(user, JsonNode.class);
            JsonNode patchedNode = patch.apply(userNode);

            UserDTO patchedUser = mapper.treeToValue(patchedNode, UserDTO.class);
            save(patchedUser);

            return patchedUser;
        } catch (JsonPatchException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid JSON Patch", e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private UserDTO findUserById(Long id) {
        return users.get(id);
    }

    private void save(UserDTO user) {
        users.put(user.getId(), user);
    }

}
