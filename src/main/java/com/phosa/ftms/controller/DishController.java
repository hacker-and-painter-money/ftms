package com.phosa.ftms.controller;

import com.phosa.ftms.constant.ErrorResponse;
import com.phosa.ftms.model.Dish;
import com.phosa.ftms.service.DishService;
import com.phosa.ftms.util.DateUtil;
import com.phosa.ftms.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping("")
    public ResponseEntity<?> getAllDishes(@RequestParam(name = "dish_name", required = false, defaultValue = "") String dishName,
                                                  @RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "page_size", defaultValue = "10") int pageSize) {
        List<Dish> dishList = dishService.list(dishName, page, pageSize);
        return ResponseUtil.getSuccessResponse(dishList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDish(@PathVariable Long id) {
        Dish dish = dishService.getById(id);
        return ResponseUtil.getSuccessResponse(dish);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDish(@PathVariable Long id) {
        Dish dish = dishService.getById(id);
        if (dish != null) {
            dishService.removeById(id);
            return ResponseUtil.getSuccessResponse(dish);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.INVALID_ID);
    }

    @PostMapping("")
    public ResponseEntity<?> addDish(@RequestBody Dish dish) {
        boolean b = dishService.save(dish);
        if (b) {
            return ResponseUtil.getSuccessResponse(dish);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDish(@PathVariable Long id, @RequestBody Dish dish) {
        Dish target = dishService.getById(id);
        if (target != null) {
            dish.setDishId(id);
            dishService.updateById(dish);
            return ResponseUtil.getSuccessResponse(dish);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.INVALID_ID);
    }
}
