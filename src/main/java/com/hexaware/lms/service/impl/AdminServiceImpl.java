package com.hexaware.lms.service.impl;

import com.hexaware.lms.Mapper.impl.CategoryMapper;
import com.hexaware.lms.dto.AddCategoryRequestBody;
import com.hexaware.lms.dto.CategoryDTO;
import com.hexaware.lms.entity.Category;
import com.hexaware.lms.repository.CategoryRepository;
import com.hexaware.lms.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Override
    public List<CategoryDTO> findAll() {
        List<Category> resultEntity = categoryRepository.findAll();
        List<CategoryDTO> resultDto = resultEntity.stream().map(it-> categoryMapper.mapTo(it)).collect(Collectors.toList());
        return resultDto;
    }

    @Override
    public CategoryDTO addCategory(AddCategoryRequestBody request) {
        Category newCategory = new Category(request.getCategory());
        Category savedCategory = categoryRepository.save(newCategory);
        return categoryMapper.mapTo(savedCategory);
    }
}