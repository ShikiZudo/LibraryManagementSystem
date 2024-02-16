package com.hexaware.lms.service;

import com.hexaware.lms.dto.AddCategoryRequestBody;
import com.hexaware.lms.dto.CategoryDTO;

import java.util.List;

public interface AdminService {
    List<CategoryDTO> findAll();

    CategoryDTO addCategory(AddCategoryRequestBody request);
}
