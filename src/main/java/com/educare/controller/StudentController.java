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

import com.educare.model.DoubtBox;
import com.educare.model.Student;
import com.educare.service.StudentService;

@CrossOrigin(origins = "*")
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
	
	@PostMapping("/logout")
	public ResponseEntity<String> logoutStudent(@RequestBody Student s){
		
		LOGGER.info("Logout Service Hit >>>>>>>");
		studentService.logoutStudent(s);
		
		
		return new ResponseEntity<>("logged out",HttpStatus.OK);
	}

	@GetMapping("/apiStatus")
	public ResponseEntity<String> findAllStudents() {

		
		return new ResponseEntity<>("Hayat Educare APIs Running", HttpStatus.OK);
	}
	
	@PostMapping("/askDoubt")
	public ResponseEntity<DoubtBox> askDoubt(@RequestBody DoubtBox db){
		
		DoubtBox response = studentService.saveDoubt(db);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PostMapping("/getDoubts")
	public ResponseEntity<List<DoubtBox>> getDoubts(@RequestBody Student s){
		
		List<DoubtBox> response = studentService.fetchAllDoubts(s);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	@PostMapping("/globalLogout")
	public ResponseEntity<String> globalLogOut(@RequestBody Student s){
		
		studentService.globalLogout(s);
		return new ResponseEntity<>("global logged out",HttpStatus.OK);
	}
	
	@PostMapping("/verifyLogin")
	public ResponseEntity<String> verifyLogin(@RequestBody Student s){
		
		String result = studentService.getCurrentActiveSessionKey(s);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

}
