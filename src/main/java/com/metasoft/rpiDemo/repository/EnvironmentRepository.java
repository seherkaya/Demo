package com.metasoft.rpiDemo.repository;

import com.metasoft.rpiDemo.model.Environment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnvironmentRepository extends JpaRepository<Environment,Integer> {

   List<Environment> findAllByActive(Integer active);
   List<Environment> findAllById(List<Environment> envArray);
   List<Environment> findAll();

   Environment findById(int id);
}