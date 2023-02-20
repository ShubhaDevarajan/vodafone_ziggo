package com.assignment.service;

import com.assignment.dto.Order;
import com.assignment.entity.Users;

import java.util.List;

public interface UserService {

    Users getUser(Order order);

    List<Users> getAllUsers(List<String> userIds);

    void save(Users users);
}
