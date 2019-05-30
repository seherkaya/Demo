package com.metasoft.rpiDemo.repository;

import com.metasoft.rpiDemo.model.User;
import com.metasoft.rpiDemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

    User findById(Integer id);
    User findByEmail(String email);
}
