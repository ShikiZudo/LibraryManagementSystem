package com.hexaware.lms.dto;

import com.hexaware.lms.utils.Gender;
import com.hexaware.lms.utils.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String email;
    private String password;
    private Role role;
    private String firstName;
    private String lastName;
    private String contactNo;
    private String address;
    private int noOfBooksLoan;
    private Gender gender;
}
