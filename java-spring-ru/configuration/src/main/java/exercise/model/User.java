package exercise.model;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String email;

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
