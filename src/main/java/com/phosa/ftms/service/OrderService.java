package com.phosa.ftms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phosa.ftms.mapper.OrderMapper;
import com.phosa.ftms.model.Order;
import com.phosa.ftms.util.AesEncryptUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {
    private final OrderDetailService orderDetailService;

    public OrderService(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    public List<Order> list(String status, Long userId, int page, int pageSize) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        if (status != null && !status.isEmpty()) {
            queryWrapper.eq("order_status", status);
        }
        if (userId != null) {
            queryWrapper.eq("user_id", userId);
        }
        List<Order> records = page(new Page<>(page, pageSize), queryWrapper).getRecords();
        for (Order record : records) {
            record.setOrderDetails(orderDetailService.list(record.getOrderId(), 1, 99999));
        }
        return records;
    }
}
