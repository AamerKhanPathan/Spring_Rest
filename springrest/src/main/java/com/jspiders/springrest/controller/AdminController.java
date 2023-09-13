package com.jspiders.springrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jspiders.springrest.pojo.AdminPOJO;
import com.jspiders.springrest.response.AdminResponse;
import com.jspiders.springrest.service.AdminService;

@RestController
public class AdminController {
@Autowired
private AdminService service;
	
	@PostMapping(path="/createAccount")
	public ResponseEntity<AdminResponse> createAccount(@RequestBody AdminPOJO admin){
		AdminPOJO pojo=service.createAccount(admin);
		if (pojo!=null) {
			return new ResponseEntity<AdminResponse>(new AdminResponse("Account created successfully. ", pojo), HttpStatus.ACCEPTED);

		}
		return new ResponseEntity<AdminResponse>(new AdminResponse("Account not created . ", null), HttpStatus.NOT_ACCEPTABLE);
}
	
	@PostMapping(path="/login")
	public ResponseEntity<AdminResponse> logIn(@RequestBody AdminPOJO admin){
		
		AdminPOJO pojo=service.login(admin);
		if (pojo!=null) {
			return new ResponseEntity<AdminResponse>(new AdminResponse("login successfully",pojo),HttpStatus.OK);
		}
		return new ResponseEntity<AdminResponse>(new AdminResponse("invalid username or password . ", null), HttpStatus.NOT_FOUND);
		
	}
}
