package com.nipa.springbootjwt.repository;

import com.nipa.springbootjwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByIdAndStatus(Long userId,Integer status);
    User findByPhoneAndStatus(String phone, Integer status);
    List<User> findAllBy ();
}
