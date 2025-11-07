package lv.nixx.poc.rest.controller.patch;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/sample/patch")
public class MapPatchController {

    private static final Logger log = LoggerFactory.getLogger(MapPatchController.class);

    @PatchMapping("/{id}")
    @Operation(summary = "Partial entity update")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Update fields in entity",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            type = "object",
                            example = "{ \"name\": \"John\", \"email\": \"john@example.com\", \"age\": 30 }"
                    )
            )
    )
    public Collection<FieldsToPatch> patchData(@PathVariable Long id, @RequestBody Map<FieldsToPatch, Object> fieldsToPatch) {
        log.info("Object with id [{}] patched", id);
        return fieldsToPatch.keySet();
    }

    public enum FieldsToPatch {
        name, email;
    }

}
