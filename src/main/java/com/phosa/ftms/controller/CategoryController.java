package com.phosa.ftms.controller;

import com.phosa.ftms.constant.ErrorResponse;
import com.phosa.ftms.model.Category;
import com.phosa.ftms.service.CategoryService;
import com.phosa.ftms.util.DateUtil;
import com.phosa.ftms.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> getAllCategorys(@RequestParam(required = false, defaultValue = "") String name,
                                                  @RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "page_size", defaultValue = "10") int pageSize) {
        List<Category> categoryList = categoryService.list(name, page, pageSize);
        return ResponseUtil.getSuccessResponse(categoryList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        return ResponseUtil.getSuccessResponse(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        if (category != null) {
            categoryService.removeById(id);
            return ResponseUtil.getSuccessResponse(category);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.INVALID_ID);
    }

    @PostMapping("")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        boolean b = categoryService.save(category);
        if (b) {
            return ResponseUtil.getSuccessResponse(category);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Category target = categoryService.getById(id);
        if (target != null) {
            category.setCategoryId(id);
            categoryService.updateById(category);
            return ResponseUtil.getSuccessResponse(category);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.INVALID_ID);
    }
}
