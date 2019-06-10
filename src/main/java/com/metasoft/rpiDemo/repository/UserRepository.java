package com.metasoft.rpiDemo.repository;

import com.metasoft.rpiDemo.model.User;
import com.metasoft.rpiDemo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

     User findByEmail(String email);
     Page findAll(Pageable pageable);
     Page findAllByActive(int active,Pageable pageable);
     Page findByName (String name, Pageable pageable);
}
