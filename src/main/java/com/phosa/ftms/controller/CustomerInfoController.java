package com.phosa.ftms.controller;

import com.phosa.ftms.constant.ErrorResponse;
import com.phosa.ftms.model.CustomerInfo;
import com.phosa.ftms.model.User;
import com.phosa.ftms.service.CustomerInfoService;
import com.phosa.ftms.service.UserService;
import com.phosa.ftms.util.DateUtil;
import com.phosa.ftms.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer_info")
public class CustomerInfoController {

    @Autowired
    private CustomerInfoService customerinfoService;
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getAllCustomerInfos(@RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "page_size", defaultValue = "10") int pageSize) {
        List<CustomerInfo> customerinfoList = customerinfoService.list(page, pageSize);
        return ResponseUtil.getSuccessResponse(customerinfoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerInfo(@PathVariable Long id) {
        CustomerInfo customerinfo = customerinfoService.getById(id);
        return ResponseUtil.getSuccessResponse(customerinfo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomerInfo(@PathVariable Long id) {
        CustomerInfo customerinfo = customerinfoService.getById(id);
        if (customerinfo != null) {
            customerinfoService.removeById(id);
            return ResponseUtil.getSuccessResponse(customerinfo);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.INVALID_ID);
    }

    @PostMapping("")
    public ResponseEntity<?> addCustomerInfo(@RequestBody CustomerInfo customerinfo) {
        boolean b = customerinfoService.save(customerinfo);
        if (b) {
            User byId = userService.getById(customerinfo.getUserId());
            byId.setIsRealName(1L);
            userService.updateById(byId);
            return ResponseUtil.getSuccessResponse(customerinfo);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomerInfo(@PathVariable Long id, @RequestBody CustomerInfo customerinfo) {
        CustomerInfo target = customerinfoService.getById(id);
        if (target != null) {
            customerinfo.setCustomerId(id);
            customerinfoService.updateById(customerinfo);
            return ResponseUtil.getSuccessResponse(customerinfo);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.INVALID_ID);
    }
}
