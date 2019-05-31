package com.metasoft.rpiDemo.repository;

import com.metasoft.rpiDemo.model.Environment;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EnvironmentRepository extends JpaRepository<Environment,Integer> {

}