package com.phosa.ftms.controller;

import com.phosa.ftms.constant.ErrorResponse;
import com.phosa.ftms.model.Role;
import com.phosa.ftms.service.RoleService;
import com.phosa.ftms.util.DateUtil;
import com.phosa.ftms.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("")
    public ResponseEntity<?> getAllRoles(@RequestParam(name = "role_name", required = false, defaultValue = "") String roleName,
                                                  @RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "page_size", defaultValue = "10") int pageSize) {
        List<Role> roleList = roleService.list(roleName, page, pageSize);
        return ResponseUtil.getSuccessResponse(roleList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRole(@PathVariable Long id) {
        Role role = roleService.getById(id);
        return ResponseUtil.getSuccessResponse(role);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        Role role = roleService.getById(id);
        if (role != null) {
            roleService.removeById(id);
            return ResponseUtil.getSuccessResponse(role);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.INVALID_ID);
    }

    @PostMapping("")
    public ResponseEntity<?> addRole(@RequestBody Role role) {
        boolean b = roleService.save(role);
        if (b) {
            return ResponseUtil.getSuccessResponse(role);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody Role role) {
        Role target = roleService.getById(id);
        if (target != null) {
            role.setRoleId(id);
            roleService.updateById(role);
            return ResponseUtil.getSuccessResponse(role);
        }
        return ResponseUtil.getFailResponse(ErrorResponse.INVALID_ID);
    }
}
