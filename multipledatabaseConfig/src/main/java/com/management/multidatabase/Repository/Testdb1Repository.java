package com.management.multidatabase.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.multidatabase.db1entiry.Test1;

@Repository
public interface Testdb1Repository extends JpaRepository<Test1,Integer> {

}
