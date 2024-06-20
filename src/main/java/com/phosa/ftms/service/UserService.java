package com.phosa.ftms.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phosa.ftms.mapper.UserMapper;
import com.phosa.ftms.model.CustomerInfo;
import com.phosa.ftms.model.User;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    private final CustomerInfoService customerInfoService;

    public UserService(CustomerInfoService customerInfoService) {
        this.customerInfoService = customerInfoService;
    }

    public boolean save(User user) {

        boolean b = super.save(user);

        user.getCustomerInfo().setUserId(user.getUserId());

        customerInfoService.save(user.getCustomerInfo());
        return b;
    }

    public List<User> getByName(String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", name);
        return list(wrapper);
    }

    public User getById(Serializable id) {
        User user = super.getById(id);
        if (user != null) {
            CustomerInfo customerInfo = customerInfoService.getById(user.getUserId());
            user.setCustomerInfo(customerInfo);
        }
        return user;
    }
    public User getUserByPhone(String phone) {
        QueryWrapper<CustomerInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("phone_number", phone);
        CustomerInfo one = customerInfoService.getOne(wrapper);
        if (one == null) {
            return null;
        }
        User user = getById(one.getUserId());
        return user;
    }

    public List<User> list(String username, int page, int pageSize) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (username != null && !username.isEmpty()) {
            queryWrapper.like("username", username);
        }
        List<User> records = page(new Page<>(page, pageSize), queryWrapper).getRecords();
        records.forEach(user -> {
            CustomerInfo customerInfo = customerInfoService.getById(user.getUserId());
            user.setCustomerInfo(customerInfo);
        });
        return records;
    }

}
