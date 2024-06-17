package com.phosa.ftms.controller;

import com.phosa.ftms.constant.ErrorResponse;
import com.phosa.ftms.model.Staff;
import com.phosa.ftms.service.StaffService;
import com.phosa.ftms.util.DateUtil;
import com.phosa.ftms.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping("")
    public ResponseEntity<?> getAllStaffs(@RequestParam(required = false, defaultValue = "") String name,
                                                  @RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "page_size", defaultValue = "10") int pageSize) {
        List<Staff> staffList = staffService.list(name, page, pageSize);
        return ResponseUtil.getSuccessResponse(staffList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStaff(@PathVariable Long id) {
        Staff staff = staffService.getById(id);
        return ResponseUtil.getSuccessResponse(staff);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable Long id) {
        Staff staff = staffService.getById(id);
        if (staff != null) {
            staffService.removeById(id);
            return ResponseUtil.getSuccessResponse(staff);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.INVALID_ID);
    }

    @PostMapping("")
    public ResponseEntity<?> addStaff(@RequestBody Staff staff) {
        boolean b = staffService.save(staff);
        if (b) {
            return ResponseUtil.getSuccessResponse(staff);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStaff(@PathVariable Long id, @RequestBody Staff staff) {
        Staff target = staffService.getById(id);
        if (target != null) {
            staff.setStaffId(id);
            staffService.updateById(staff);
            return ResponseUtil.getSuccessResponse(staff);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.INVALID_ID);
    }

    //login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Staff staff) {
        Staff target = staffService.login(staff.getName(), staff.getPassword());
        if (target != null) {
            return ResponseUtil.getSuccessResponse(target);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.WRONG_PASSWORD);
    }
}
