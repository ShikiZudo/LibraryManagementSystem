package com.hexaware.lms.Mapper.impl;

import com.hexaware.lms.Mapper.Mapper;
import com.hexaware.lms.dto.CategoryDTO;
import com.hexaware.lms.entity.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapper implements Mapper<Category, CategoryDTO> {

    private final ModelMapper modelMapper;

    @Override
    public CategoryDTO mapTo(Category category) {
        return modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public Category mapFrom(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO,Category.class);
    }
}
