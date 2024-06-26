package com.phosa.ftms.controller;

import com.phosa.ftms.constant.ErrorResponse;
import com.phosa.ftms.model.Dish;
import com.phosa.ftms.model.OrderDetail;
import com.phosa.ftms.service.OrderDetailService;
import com.phosa.ftms.util.DateUtil;
import com.phosa.ftms.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order_detail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderdetailService;

    @GetMapping("")
    public ResponseEntity<?> getAllOrderDetails(@RequestParam(name = "order_id") long orderId,
                                                  @RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "page_size", defaultValue = "10") int pageSize) {
        List<OrderDetail> orderdetailList = orderdetailService.list(orderId, page, pageSize);
        return ResponseUtil.getSuccessResponse(orderdetailList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetail(@PathVariable Long id) {
        OrderDetail orderdetail = orderdetailService.getById(id);
        return ResponseUtil.getSuccessResponse(orderdetail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderDetail(@PathVariable Long id) {
        OrderDetail orderdetail = orderdetailService.getById(id);
        if (orderdetail != null) {
            orderdetailService.removeById(id);
            return ResponseUtil.getSuccessResponse(orderdetail);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.INVALID_ID);
    }

    @PostMapping("")
    public ResponseEntity<?> addOrderDetail(@RequestBody OrderDetail orderdetail) {
        boolean b = orderdetailService.save(orderdetail);
        if (b) {
            return ResponseUtil.getSuccessResponse(orderdetail);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderDetail(@PathVariable Long id, @RequestBody OrderDetail orderdetail) {
        OrderDetail target = orderdetailService.getById(id);
        if (target != null) {
            orderdetail.setDetailId(id);
            orderdetailService.updateById(orderdetail);
            return ResponseUtil.getSuccessResponse(orderdetail);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.INVALID_ID);
    }
    // 统计接口，统计每一个菜品的销售量
    @GetMapping("/statistics")
    public ResponseEntity<?> statistics() {
        Map<String, Integer> map = orderdetailService.statistics();
        return ResponseUtil.getSuccessResponse(map);
    }
}
