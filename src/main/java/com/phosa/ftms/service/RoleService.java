package com.phosa.ftms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phosa.ftms.mapper.RoleMapper;
import com.phosa.ftms.model.Role;
import com.phosa.ftms.util.AesEncryptUtil;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {

    // Example method for login (if applicable)
    public Role login(String identifier, String password) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq("identifier_column", identifier);
        Role entity = getOne(wrapper);
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
