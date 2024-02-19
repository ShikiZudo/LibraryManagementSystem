package com.hexaware.lms.dto;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {


    @UniqueElements
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
//    @Pattern.List({
//            @Pattern(regexp = ".*[a-z].*", message = "Password must contain at least one lowercase letter"),
//            @Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least one uppercase letter"),
//            @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one digit"),
//            @Pattern(regexp = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>/?].*", message = "Password must contain at least one special character")
//    })
    String password;
}
