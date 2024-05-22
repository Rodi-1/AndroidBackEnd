package com.rodi1.androidbackend.service;

import com.rodi1.androidbackend.controller.dto.UserProfileDto;
import com.rodi1.androidbackend.controller.dto.UserRegisterDto;
import com.rodi1.androidbackend.entity.Authority;
import com.rodi1.androidbackend.entity.User;

import java.util.List;

public interface UserService {
    UserProfileDto add(UserRegisterDto userRegisterDto);

    List<UserProfileDto> getAll();

    UserProfileDto getById(Long id);

    UserProfileDto getByUsername(String username);

    UserProfileDto update(Long id, UserProfileDto userProfileDto);

    void update(Long id, Authority authority);

    void deleteById(Long id);
}
