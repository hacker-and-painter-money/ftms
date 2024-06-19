package com.phosa.ftms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phosa.ftms.mapper.CustomerInfoMapper;
import com.phosa.ftms.model.CustomerInfo;
import com.phosa.ftms.util.AesEncryptUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerInfoService extends ServiceImpl<CustomerInfoMapper, CustomerInfo> {

    public List<CustomerInfo> list(int page, int pageSize) {
        QueryWrapper<CustomerInfo> queryWrapper = new QueryWrapper<>();
        return page(new Page<>(page, pageSize), queryWrapper).getRecords();
    }
}
