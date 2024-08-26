package com.management.multidatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.multidatabase.db2entity.Test;
import com.management.multidatabase.db2repository.Testdb2Repository;


@Service
public class Testdb2ServiceImpl {

	@Autowired
	private Testdb2Repository testdb2Repository;
	
	public Test saveUser(Test test)
	{
		return testdb2Repository.save(test);
	}
}
