package com.phosa.ftms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phosa.ftms.mapper.UserMapper;
import com.phosa.ftms.model.User;
import com.phosa.ftms.util.AesEncryptUtil;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    // Example method for login (if applicable)
    public User login(String identifier, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("identifier_column", identifier);
        User entity = getOne(wrapper);
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
