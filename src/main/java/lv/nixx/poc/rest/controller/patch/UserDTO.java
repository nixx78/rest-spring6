package lv.nixx.poc.rest.controller.patch;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private Integer age;
}