package com.multitenancy.controller;

import com.multitenancy.entity.User;
import com.multitenancy.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {

        return ResponseEntity.ok(userService.findAll());

    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user, @RequestHeader("X-Tenant-ID") String tenantId) {
        user.setTenantId(tenantId);
        return ResponseEntity.ok(userService.save(user));
    }

}
