package com.rodi1.androidbackend.service;


import com.rodi1.androidbackend.entity.Authority;

import java.util.List;

public interface AuthorityService {
    Authority add(Authority authority);

    List<Authority> getAll();
}