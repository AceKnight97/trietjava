package com.demoDigital.demo.services;

import com.demoDigital.demo.model.User;
import com.demoDigital.demo.model.PersonalInfo;

import java.util.List;

public interface UserService {

    User save(PersonalInfo user);

    List<User> findAll();

    void delete(Long id);

    User findOne(String username);

    User findById(Long id);

    PersonalInfo update(PersonalInfo userDto);
}
