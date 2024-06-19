package com.phosa.ftms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phosa.ftms.mapper.UserMapper;
import com.phosa.ftms.model.CustomerInfo;
import com.phosa.ftms.model.User;
import com.phosa.ftms.util.AesEncryptUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    private final CustomerInfoService customerInfoService;

    public UserService(CustomerInfoService customerInfoService) {
        this.customerInfoService = customerInfoService;
    }

    public User getUserByPhone(String phone) {
        QueryWrapper<CustomerInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("phone_number", phone);
        CustomerInfo one = customerInfoService.getOne(wrapper);
        if (one == null) {
            return null;
        }
        User user = getById(one.getUserId());
        user.setCustomerInfo(one);
        return user;
    }

    public List<User> list(String username, int page, int pageSize) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (username != null && !username.isEmpty()) {
            queryWrapper.like("username", username);
        }
        return page(new Page<>(page, pageSize), queryWrapper).getRecords();
    }

}
