package com.metasoft.rpiDemo.repository;

import com.metasoft.rpiDemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findById(int id);

    Set<Role> findByRole(Set<Role> role);

    Role findByRole(String roles);

    List<Role> findAllBy();
}
