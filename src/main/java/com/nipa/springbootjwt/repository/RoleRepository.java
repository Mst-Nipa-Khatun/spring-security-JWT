package com.nipa.springbootjwt.repository;

import com.nipa.springbootjwt.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    long countByNameAndStatus(String roleName, Integer status);

    Role findByNameAndStatus(String roleName,Integer status);
}
