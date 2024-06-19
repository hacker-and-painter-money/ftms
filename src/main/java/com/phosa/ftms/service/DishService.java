package com.phosa.ftms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phosa.ftms.mapper.DishMapper;
import com.phosa.ftms.model.Dish;
import com.phosa.ftms.util.AesEncryptUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService extends ServiceImpl<DishMapper, Dish> {

    public List<Dish> list(String name, int page, int pageSize) {
        QueryWrapper<Dish> queryWrapper = new QueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            queryWrapper.like("dish_name", name);
        }
        return page(new Page<>(page, pageSize), queryWrapper).getRecords();
    }
}
