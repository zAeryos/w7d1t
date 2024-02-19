package it.epicode.w7d1t.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "Name is mandatory")
    private  String  name;
    @NotBlank(message = "Surname is mandatory")
    private  String  surname;
    @NotBlank(message = "Username is mandatory")
    private  String  username;
    @NotBlank(message = "Password is mandatory")
    private  String  password;
}
