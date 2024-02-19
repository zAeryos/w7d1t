package it.epicode.w7d1t.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeRequest {

    @NotNull    (message = "Name is mandatory")
    @NotEmpty   (message = "The name cannot be empty")
    private String name;
    @NotNull    (message = "Surname is mandatory")
    @NotEmpty   (message = "The surname cannot be empty")
    private String surname;
    @NotNull    (message = "Username is mandatory")
    @NotEmpty   (message = "The username cannot be empty")
    private String username;
    @Email      (message = "The email format is not valid")
    private String email;

}
