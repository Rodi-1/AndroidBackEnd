package com.rodi1.androidbackend.mapper;


import com.rodi1.androidbackend.controller.dto.UserProfileDto;
import com.rodi1.androidbackend.controller.dto.UserRegisterDto;
import com.rodi1.androidbackend.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public User toUserEntity(UserProfileDto userProfileDto) {
        return User.builder() // НЕТ ЗАЩИТЫ ОТ NullPointerExeption если приходят пустые поля
                .id(userProfileDto.getId())
                .name(userProfileDto.getName())
                .username(userProfileDto.getUsername())
                .email(userProfileDto.getEmail())
                .phone(userProfileDto.getPhone())
                .photoUrl(userProfileDto.getPhotoUrl())
                .build();
    }

    public User toUserEntity(UserRegisterDto userRegisterDto) {
        User user = User.builder()
                .username(userRegisterDto.getUsername())
                .password(userRegisterDto.getPassword())
                .email(userRegisterDto.getEmail())
                .build();

        if(userRegisterDto.getId() !=null) user.setId(userRegisterDto.getId());

        return user;
    }

    public UserProfileDto toUserProfileDto(User user) {
        return UserProfileDto.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .photoUrl(user.getPhotoUrl())
                .build();
    }


}
