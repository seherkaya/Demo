package com.metasoft.rpiDemo.repository;

import com.metasoft.rpiDemo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {


     /**/
     User findById(int id);
     User findByUserEmail(String userEmail);

     Page findAll(Pageable pageable);


     Page findAllByActive(int active,Pageable pageable);
     Page findByUserName (String userName, Pageable pageable);


     /*@Query("select u from user u where u.user_name like %?1")*/
     Page findByUserNameContaining (String userName,Pageable pageable);
}
