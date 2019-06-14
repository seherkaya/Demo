package com.metasoft.rpiDemo.repository;

import com.metasoft.rpiDemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    List<Role> findByRole(String role);
    List<Role> findAllBy();
}
