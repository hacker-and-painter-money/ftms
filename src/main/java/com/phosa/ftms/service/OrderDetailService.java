package com.phosa.ftms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phosa.ftms.mapper.OrderDetailMapper;
import com.phosa.ftms.model.Dish;
import com.phosa.ftms.model.OrderDetail;
import com.phosa.ftms.util.AesEncryptUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // 统计每一个菜品的销量
    public Map<String, Integer> statistics() {
        QueryWrapper<OrderDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.groupBy("dish_id");
        queryWrapper.select("dish_id", "sum(quantity) as count");
        List<Map<String, Object>> maps = listMaps(queryWrapper);
        Map<String, Integer> result = new HashMap<>();
        for (Map<String, Object> map : maps) {
            Long dishId = Long.parseLong(String.valueOf((Integer)map.get("dish_id")));
            Dish dish = dishService.getById(dishId);
            result.put(dish.getDishName(), Integer.valueOf(String.valueOf(map.get("count"))));
        }
        return result;
    }
}
