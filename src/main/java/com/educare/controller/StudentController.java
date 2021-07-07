package com.educare.controller;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

	private static final Logger LOGGER = LogManager.getLogger();
	
	@Autowired
	StudentService studentService;

	@PostMapping("/enroll")
	public ResponseEntity<Student> createStudentAccount(@RequestBody Student s){
		
		LOGGER.info("Enrollment Service Hit >>>>>>>");
		Student response = studentService.createStudentAccount(s);
		
		LOGGER.info("Enrollment Service Response >>>>>>> {}",response);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<Student> loginStudent(@RequestBody Student s){
		
		LOGGER.info("Login Service Hit >>>>>>>");
		Student response = studentService.loginStudent(s);
		
		LOGGER.info("Login Reponse >>>>>>> {}",response);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	@GetMapping("/findall")
	public ResponseEntity<List<Student>> findAllStudents() {

		List<Student> students = studentService.findAll();
		return new ResponseEntity<>(students, HttpStatus.OK);
	}

}
