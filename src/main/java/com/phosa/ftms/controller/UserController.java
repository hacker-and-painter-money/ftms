package com.phosa.ftms.controller;

import com.phosa.ftms.constant.ErrorResponse;
import com.phosa.ftms.model.Staff;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CustomerInfoService customerInfoService;

    @GetMapping("")
    public ResponseEntity<?> getAllUsers(@RequestParam(required = false, defaultValue = "") String username,
                                                  @RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "page_size", defaultValue = "10") int pageSize) {
        List<User> userList = userService.list(username, page, pageSize);
        return ResponseUtil.getSuccessResponse(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        User user = userService.getById(id);
        return ResponseUtil.getSuccessResponse(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user != null) {
            userService.removeById(id);
            return ResponseUtil.getSuccessResponse(user);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.INVALID_ID);
    }

    @PostMapping("")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        User userByPhone = userService.getUserByPhone(user.getCustomerInfo().getPhoneNumber());
        if (userByPhone != null) {
            return ResponseUtil.getSuccessResponse(user);
        }

        boolean b = userService.save(user);
        if (b) {
            return ResponseUtil.getSuccessResponse(user);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        User target = userService.getById(id);
        if (target != null) {
            user.setUserId(id);
            userService.updateById(user);
            return ResponseUtil.getSuccessResponse(user);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.INVALID_ID);
    }

    @GetMapping("/phone")
    public ResponseEntity<?> getUserByPhone(@RequestParam String phone) {
        User userByPhone = userService.getUserByPhone(phone);
        if (userByPhone != null ) {
            return ResponseUtil.getSuccessResponse(userByPhone);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.PHONE_NOT_EXIST);

    }


    //login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User target = userService.login(user.getUsername(), user.getPassword());
        if (target != null) {
            return ResponseUtil.getSuccessResponse(target);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.WRONG_PASSWORD);
    }
}
