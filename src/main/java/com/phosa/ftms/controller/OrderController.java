package com.phosa.ftms.controller;

import com.phosa.ftms.constant.ErrorResponse;
import com.phosa.ftms.model.Order;
import com.phosa.ftms.model.User;
import com.phosa.ftms.service.OrderDetailService;
import com.phosa.ftms.service.OrderService;
import com.phosa.ftms.service.UserService;
import com.phosa.ftms.util.DateUtil;
import com.phosa.ftms.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getAllOrders(@RequestParam(required = false, defaultValue = "") String status,
                                                  @RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "page_size", defaultValue = "10") int pageSize) {
        List<Order> orderList = orderService.list(status, page, pageSize);
        return ResponseUtil.getSuccessResponse(orderList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        Order order = orderService.getById(id);
        return ResponseUtil.getSuccessResponse(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        Order order = orderService.getById(id);
        if (order != null) {
            orderService.removeById(id);
            return ResponseUtil.getSuccessResponse(order);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.INVALID_ID);
    }

    @PostMapping("")
    public ResponseEntity<?> addOrder(@RequestBody Order order) {
        userService.save(order.getUser());
        order.setUserId(order.getUser().getUserId());
        orderService.save(order);
        Long orderId = order.getOrderId();
        order.getOrderDetails().forEach(orderDetail -> orderDetail.setOrderId(orderId));
        boolean b = orderDetailService.saveBatch(order.getOrderDetails());
        if (b) {
            return ResponseUtil.getSuccessResponse(order);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        Order target = orderService.getById(id);
        if (target != null) {
            order.setOrderId(id);
            orderService.updateById(order);
            return ResponseUtil.getSuccessResponse(order);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.INVALID_ID);
    }
}
