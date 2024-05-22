package com.rodi1.androidbackend.service.impl;

import com.rodi1.androidbackend.controller.dto.UserProfileDto;
import com.rodi1.androidbackend.controller.dto.UserRegisterDto;
import com.rodi1.androidbackend.dao.AuthorityRepository;
import com.rodi1.androidbackend.dao.UserRepository;
import com.rodi1.androidbackend.entity.Authority;
import com.rodi1.androidbackend.entity.User;
import com.rodi1.androidbackend.exception.UserAlreadyExistsException;
import com.rodi1.androidbackend.exception.UserNotFoundException;
import com.rodi1.androidbackend.mapper.UserMapper;
import com.rodi1.androidbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserProfileDto add(UserRegisterDto userRegisterDto) {
        if (userRepository.findByUsername(userRegisterDto.getUsername()).isPresent())
            throw new UserAlreadyExistsException("User with username " + userRegisterDto.getUsername() + " already exists");

        Optional<Authority> authorityOptional = authorityRepository.findByAuthority("ROLE_USER");
        if (!authorityOptional.isPresent())
            throw new RuntimeException("Authority ROLE_USER not found"); // TODO: Change this exception

        User user = UserMapper.toUserEntity(userRegisterDto);
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        Set<Authority> authoritiesSet = new HashSet<>();
        authoritiesSet.add(authorityOptional.get());
        user.setAuthorities(authoritiesSet);

        User save = userRepository.save(user);

        return UserMapper.toUserProfileDto(save);
    }

    @Override
    public List<UserProfileDto> getAll() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(UserMapper::toUserProfileDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserProfileDto getById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent())
            throw new UserNotFoundException("User with id " + id + " not found");

        return UserMapper.toUserProfileDto(optionalUser.get());
    }

    @Override
    public UserProfileDto update(Long id, UserProfileDto userProfileDto) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent())
            throw new UserNotFoundException("User with id " + id + " not found");

        User user = optionalUser.get();

        if(userProfileDto.getName() != null) user.setName(userProfileDto.getName());
        if(userProfileDto.getUsername() != null) user.setUsername(userProfileDto.getUsername());
        if(userProfileDto.getEmail() != null) user.setEmail(userProfileDto.getEmail());
        if(userProfileDto.getPhone() != null) user.setPhone(userProfileDto.getPhone());
        if(userProfileDto.getPhotoUrl() != null) user.setPhotoUrl(userProfileDto.getPhotoUrl());


        User save = userRepository.save(user);
        return UserMapper.toUserProfileDto(save);
    }

    @Override
    public void update(Long id, Authority authority) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) throw new UserNotFoundException("User with ID " + id + " not found");
        Optional<Authority> authorityOptional = authorityRepository.findByAuthority(authority.getAuthority());
        if (!authorityOptional.isPresent()) throw new RuntimeException("Authority not found!");

        User user = userOptional.get();
        authority = authorityOptional.get();

        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);
        user.setAuthorities(authorities);

        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) throw new UserNotFoundException("User with id " + id + " not found");

        userRepository.deleteById(id);


    }

    @Override
    public UserProfileDto getByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (!userOptional.isPresent()) throw new UserNotFoundException("User with username " + username + " not found");

        return UserMapper.toUserProfileDto(userOptional.get());
    }

}
