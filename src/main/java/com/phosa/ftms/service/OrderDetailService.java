package com.phosa.ftms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phosa.ftms.mapper.OrderDetailMapper;
import com.phosa.ftms.model.OrderDetail;
import com.phosa.ftms.util.AesEncryptUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService extends ServiceImpl<OrderDetailMapper, OrderDetail> {

    // Add other CRUD or business-specific methods here
    public List<OrderDetail> list(int page, int pageSize) {
        QueryWrapper<OrderDetail> queryWrapper = new QueryWrapper<>();
        return page(new Page<>(page, pageSize), queryWrapper).getRecords();
    }
}
