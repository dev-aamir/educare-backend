package com.educare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educare.model.Student;
import com.educare.service.StudentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@PostMapping("/enroll")
	public ResponseEntity<Student> createStudentAccount(@RequestBody Student s){
		
		Student response = studentService.createStudentAccount(s);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<Student> loginStudent(@RequestBody Student s){
		
		Student response = studentService.loginStudent(s);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
}
