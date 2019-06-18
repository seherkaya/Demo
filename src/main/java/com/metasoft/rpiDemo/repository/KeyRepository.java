package com.metasoft.rpiDemo.repository;

import com.metasoft.rpiDemo.model.Key;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeyRepository extends JpaRepository<Key,Integer> {

    Key findByKeyName( String keyName );

    List<Key> findAllByActive(Integer active);
    List<Key> findAllById(List<Key> envArray);
    List<Key> findAll();

    Key findById(int id);
}