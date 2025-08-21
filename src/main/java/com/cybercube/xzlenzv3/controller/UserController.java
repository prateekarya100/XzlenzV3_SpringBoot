package com.cybercube.xzlenzv3.controller;

import com.cybercube.xzlenzv3.model.Client.dto.CreateUserRequest;
import com.cybercube.xzlenzv3.model.User.User;
import com.cybercube.xzlenzv3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody CreateUserRequest req) {
        return userService.createUser(req);
    }

    @PutMapping("/{id}/status")
    public String changeStatus(@PathVariable Integer id, @RequestParam String status) {
        userService.changeStatus(id, status);
        return "Status updated to "+status;
    }
}
