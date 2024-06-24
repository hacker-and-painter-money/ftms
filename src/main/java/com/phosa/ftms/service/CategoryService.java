package com.phosa.ftms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phosa.ftms.mapper.CategoryMapper;
import com.phosa.ftms.model.Category;
import com.phosa.ftms.util.AesEncryptUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService extends ServiceImpl<CategoryMapper, Category> {


    public Category getCategoryByName(String name) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name", name);
        Category one = getOne(queryWrapper);
        if (one == null) {
            return new Category(-1L, "其他");
        }
        return one;
    }

    public List<Category> list(String name, int page, int pageSize) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            queryWrapper.like("category_name", name);
        }
        return page(new Page<>(page, pageSize), queryWrapper).getRecords();
    }
}
