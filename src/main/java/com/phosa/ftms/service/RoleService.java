package com.phosa.ftms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phosa.ftms.mapper.RoleMapper;
import com.phosa.ftms.model.Category;
import com.phosa.ftms.model.Role;
import com.phosa.ftms.util.AesEncryptUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {

    // Add other CRUD or business-specific methods here
    public List<Role> list(String name, int page, int pageSize) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            queryWrapper.like("role_name", name);
        }
        return page(new Page<>(page, pageSize), queryWrapper).getRecords();

    }
}
