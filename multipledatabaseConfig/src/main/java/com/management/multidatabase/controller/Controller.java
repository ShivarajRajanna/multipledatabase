package com.management.multidatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.management.multidatabase.db1entiry.Test1;
import com.management.multidatabase.db2entity.Test;
import com.management.multidatabase.service.Testdb1ServiceImpl;
import com.management.multidatabase.service.Testdb2ServiceImpl;

@RestController
public class Controller {
	@Autowired
	private Testdb1ServiceImpl testdb1ServiceImpl;
	@Autowired
	private Testdb2ServiceImpl testdb2ServiceImpl;
	
	@PostMapping("saveuserdb1")
	public ResponseEntity saveUserdb1(@RequestBody Test1 test1)
	{
		Test1 t1=testdb1ServiceImpl.saveUser(test1);
		return new ResponseEntity(t1, HttpStatus.OK);
	}
	
	@PostMapping("saveuserdb2")
	public ResponseEntity saveUserdb2(@RequestBody Test test)
	{
		Test t=testdb2ServiceImpl.saveUser(test);
		return new ResponseEntity(t, HttpStatus.OK);
	}

}
