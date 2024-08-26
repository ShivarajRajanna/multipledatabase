package com.management.multidatabase.db2repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.multidatabase.db2entity.Test;
@Repository
public interface Testdb2Repository extends JpaRepository<Test,Integer> {

}
