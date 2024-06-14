package com.phosa.ftms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phosa.ftms.mapper.DishMapper;
import com.phosa.ftms.model.Dish;
import com.phosa.ftms.util.AesEncryptUtil;
import org.springframework.stereotype.Service;

@Service
public class DishService extends ServiceImpl<DishMapper, Dish> {

    // Example method for login (if applicable)
    public Dish login(String identifier, String password) {
        QueryWrapper<Dish> wrapper = new QueryWrapper<>();
        wrapper.eq("identifier_column", identifier);
        Dish entity = getOne(wrapper);
        if (entity != null) {
            try {
                if (entity.getPassword().equals(AesEncryptUtil.encrypt(password))) {
                    return entity;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    // Add other CRUD or business-specific methods here
}
