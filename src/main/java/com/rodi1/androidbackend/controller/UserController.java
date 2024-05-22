package com.rodi1.androidbackend.controller;

import com.rodi1.androidbackend.entity.Authority;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public UserProfileDto add(@RequestBody UserRegisterDto userRegisterDto) {
        return userService.add(userRegisterDto);
    }

    @GetMapping("user")
    @ResponseStatus(HttpStatus.OK)
    public List<UserProfileDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserProfileDto getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PutMapping("user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserProfileDto update(@PathVariable Long id, @RequestBody UserProfileDto userProfileDto) {
        return userService.update(id, userProfileDto);
    }

    @PutMapping("/authority/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id, @RequestBody Authority authority) {
        userService.update(id, authority);
    }

    @DeleteMapping("user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @GetMapping("user/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public String getByUsername(@PathVariable String username) {
        UserProfileDto byUsername =  userService.getByUsername(username);

        return "User with username " + byUsername.getUsername() + " is registered!";
    }

    @GetMapping("user/login")
    @ResponseStatus(HttpStatus.OK)
    public UserProfileDto login(Authentication authentication) {
        return userService.getByUsername(authentication.getName());
    }





}
