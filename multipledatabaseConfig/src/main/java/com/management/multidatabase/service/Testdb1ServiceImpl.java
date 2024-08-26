package com.management.multidatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.multidatabase.Repository.Testdb1Repository;
import com.management.multidatabase.db1entiry.Test1;

@Service
public class Testdb1ServiceImpl {
	
	@Autowired
	private Testdb1Repository testdb1Repository;
	
	@Transactional
	public Test1 saveUser(Test1 test)
	{
		return testdb1Repository.save(test);
	}

}
