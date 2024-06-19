package com.phosa.ftms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phosa.ftms.mapper.StaffMapper;
import com.phosa.ftms.model.Staff;
import com.phosa.ftms.model.User;
import com.phosa.ftms.util.AesEncryptUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService extends ServiceImpl<StaffMapper, Staff> {
    public Staff login(String identifier, String password) {
        QueryWrapper<Staff> wrapper = new QueryWrapper<>();
        wrapper.eq("name", identifier);
        Staff entity = getOne(wrapper);
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
    public List<Staff> list(String name, int page, int pageSize) {
        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            queryWrapper.like("name", name);
        }
        return page(new Page<>(page, pageSize), queryWrapper).getRecords();
    }

}
