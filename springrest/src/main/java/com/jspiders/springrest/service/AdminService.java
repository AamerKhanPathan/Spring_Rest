package com.jspiders.springrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspiders.springrest.pojo.AdminPOJO;
import com.jspiders.springrest.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired 
	private AdminRepository repository;
	
	public AdminPOJO createAccount(AdminPOJO admin) {
		AdminPOJO pojo=repository.createAccount(admin);
		return pojo;
	}

	public AdminPOJO login(AdminPOJO admin) {
  
		AdminPOJO pojo=repository.login(admin);
		return pojo;
	}

}
