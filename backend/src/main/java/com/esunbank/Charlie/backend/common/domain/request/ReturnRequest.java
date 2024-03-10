package com.esunbank.Charlie.backend.common.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReturnRequest {
    @NotNull(message = "isbn is undefined.")
    @NotBlank(message = "please input isbn.")
    @Size(min = 13, max = 13, message = "isbn must be 13 digits.")
    @Pattern(regexp = "[0-9]{13}", message = "isbn must be 13 digits.")    
    private String isbn;
    @NotNull(message = "userId is undefined.")
    @NotBlank(message = "please input userId.")
    private Long userId;
}
