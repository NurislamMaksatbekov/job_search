package com.example.hw49.service;

import com.example.hw49.dao.CategoryDao;
import com.example.hw49.dto.CategoryDto;
import com.example.hw49.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryDao categoryDao;

    public String getCategoryById(Long id){
        return categoryDao.getCategoryById(id);
    }
}
