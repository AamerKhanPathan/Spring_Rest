package com.jspiders.springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jspiders.springrest.pojo.StudentPOJO;
import com.jspiders.springrest.response.StudentResponse;
import com.jspiders.springrest.service.StudentService;

@Controller
public class StudentController {
	
	
	@Autowired
	private  StudentService service;
	
	//add record
	
	//marshalling and unmarshaliing (produces and consumes) will occurs implicitly also..
	
	
	@PostMapping(path="/add")
	public ResponseEntity<StudentResponse> addStudnet(@RequestBody StudentPOJO pojo){
		
		StudentPOJO student=service.addStudent(pojo);
		
		
		//success
		if (student!=null){
			
			return new ResponseEntity<StudentResponse>(new StudentResponse("data inserted successfully",
														student,null), HttpStatus.ACCEPTED);
		}
		
		//failure
		return new ResponseEntity<StudentResponse>(new StudentResponse("data not added", 
																		null, null),HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	//search Record
	@GetMapping(path="/search/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponse> searchStudent(@PathVariable int id ){
	StudentPOJO student=service.searchStudent(id);
	//success flow
	if (student!=null) {
		return new ResponseEntity<StudentResponse>(new StudentResponse("data found successfully",
				student,null), HttpStatus.FOUND);
	}
	//failure flow
	return new ResponseEntity<StudentResponse>(new StudentResponse("data not found",
			null,null), HttpStatus.NOT_FOUND);
		
		}
	
	//search all records
	
	@GetMapping(path = "/searchAll",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponse> searchAllStudents(){
		
	  List<StudentPOJO> students=service.searchAllStudents();
	  
	  //success flow
	  if (students.isEmpty()==false) {
		  return new ResponseEntity<StudentResponse>(new StudentResponse("data  found",
					null,students), HttpStatus.NOT_FOUND);
		
	}
	  //failure flow
	  return new ResponseEntity<StudentResponse>(new StudentResponse("data not found",
				null,null), HttpStatus.NOT_FOUND);
	}
	
	//remove record
	@DeleteMapping(path="/remove/{id}")
	public ResponseEntity<StudentResponse> removeStudent(@PathVariable int id){
	StudentPOJO student=service.removeStudent(id);
	//success flow
	if (student!=null) {
	return new ResponseEntity<StudentResponse>(new  StudentResponse("record removed successfully",student,null),HttpStatus.OK);
	}
	//failure flow
	return new ResponseEntity<StudentResponse>(new  StudentResponse("record not found to remove ",null,null),HttpStatus.BAD_REQUEST);

	
	}
	
	//update record
	@PutMapping(path="/update")
	public ResponseEntity<StudentResponse> updateStudent(@RequestBody StudentPOJO pojo){
		
		StudentPOJO student=service.updateStudent(pojo);
		if (student!=null) {
			return new ResponseEntity<StudentResponse>(new StudentResponse("updated successfully",student,null),HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<StudentResponse>(new StudentResponse(" record not found for updation / not updated ",null,null),HttpStatus.NOT_ACCEPTABLE);
	}

}
