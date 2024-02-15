package exercise.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

// BEGIN
@Setter
@Getter
public class GuestCreateDTO {
    @NotNull
    private String name;

    /*@Min(12)
    @Max(14)*/
    @Pattern(regexp = "\\+\\d{11,13}")
    private String phoneNumber;

    @Size(min = 4,max = 4)
    private String clubCard;

    @FutureOrPresent
    private LocalDate cardValidUntil;

    @Email
    private String email;

}
// END
