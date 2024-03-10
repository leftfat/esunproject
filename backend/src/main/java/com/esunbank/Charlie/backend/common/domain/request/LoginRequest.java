package com.esunbank.Charlie.backend.common.domain.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @NotNull(message = "phone number is undefined.")
    @NotBlank(message = "please input phone number.")
    @Size(min = 10, max = 10, message = "phone number must be 10 digits.")
    @Pattern(regexp = "09[0-9]{8}", message = "phone number must be 09XXXXXXXX.")
    private String phoneNumber;
    @NotNull(message = "password is undefined.")
    @NotBlank(message = "please input password.")
    @Size(min = 8, max = 20, message = "password must be 8-20 characters.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "password must contain at least one letter and one number.")
    private String password;
}
