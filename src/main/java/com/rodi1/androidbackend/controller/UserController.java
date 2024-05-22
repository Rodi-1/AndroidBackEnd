package com.rodi1.androidbackend.controller;

import org.springframework.security.core.Authentication;
import com.rodi1.androidbackend.controller.dto.UserProfileDto;
import com.rodi1.androidbackend.controller.dto.UserRegisterDto;
import com.rodi1.androidbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/androidbackend/v1/")
public class UserController {

    private final UserService userService;

    @PostMapping("user/register")
    public UserProfileDto add(@RequestBody UserRegisterDto userRegisterDto) {
        return userService.add(userRegisterDto);
    }

    @GetMapping("user")
    public List<UserProfileDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("user/{id}")
    public UserProfileDto getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PutMapping("user/{id}")
    public UserProfileDto update(@PathVariable Long id, @RequestBody UserProfileDto userProfileDto) {
        return userService.update(id, userProfileDto);
    }

    @DeleteMapping("user/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @GetMapping("user/username/{username}")
    public String getByUsername(@PathVariable String username) {
        UserProfileDto byUsername =  userService.getByUsername(username);

        return "User with username " + byUsername.getUsername() + " is registered!";
    }

    @GetMapping("user/login")
    public UserProfileDto login(Authentication authentication) {
        return userService.getByUsername(authentication.getName());
    }





}
