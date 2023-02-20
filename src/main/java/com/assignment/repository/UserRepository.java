package com.assignment.repository;

import com.assignment.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, String> {

    List<Users> findAllByEmailIn(List<String> email);
}

