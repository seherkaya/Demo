package com.metasoft.rpiDemo.repository;

import com.metasoft.rpiDemo.model.Environment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnvironmentRepository extends JpaRepository<Environment,Integer> {

   List<Environment> findAllByActive(Integer active);

   Environment findById(int id);
}