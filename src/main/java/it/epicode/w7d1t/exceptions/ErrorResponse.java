package it.epicode.w7d1t.exceptions;

import lombok.Data;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Data
public class ErrorResponse {
    private int             status;
    private String          message;
    private LocalDateTime   dataError;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        dataError = LocalDateTime.now();
    }
    public static String handleValidationMessages(BindingResult bindingResult){
        return bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining(","));
    }
}
