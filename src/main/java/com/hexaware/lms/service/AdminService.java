package com.hexaware.lms.service;

import com.hexaware.lms.dto.AddCategoryRequestBody;
import com.hexaware.lms.dto.CategoryDTO;
import com.hexaware.lms.dto.NotificationDTO;
import com.hexaware.lms.exception.ResourceNotFoundException;

import java.util.List;

public interface AdminService {
    List<CategoryDTO> findAll();

    CategoryDTO addCategory(AddCategoryRequestBody request);

    void deleteCategory(long id) throws ResourceNotFoundException;


    NotificationDTO sendReturnRequest(NotificationDTO notificationDTO) throws ResourceNotFoundException;
}
