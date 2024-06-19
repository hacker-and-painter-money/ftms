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

    private final DishService dishService;

    public OrderDetailService(DishService dishService) {
        this.dishService = dishService;
    }

    public List<OrderDetail> list(long orderId, int page, int pageSize) {
        QueryWrapper<OrderDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        List<OrderDetail> records = page(new Page<>(page, pageSize), queryWrapper).getRecords();
        records.forEach(orderDetail -> orderDetail.setDishName(dishService.getById(orderDetail.getDishId()).getDishName()));
        return records;
    }
}
