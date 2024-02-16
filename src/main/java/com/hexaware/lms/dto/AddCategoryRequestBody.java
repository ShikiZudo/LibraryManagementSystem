package com.hexaware.lms.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddCategoryRequestBody {
    @Size(max = 20, min = 3, message = "Invalid category. Size should be between 3 to 20.")
    public String category;
}

