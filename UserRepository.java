package com.assignment.repository;

import com.assignment.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

    List<Users> findAllByEmailIn(List<String> email);
}

