package com.educare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educare.model.Course;
import com.educare.model.Enquiry;
import com.educare.model.Playlist;
import com.educare.model.Standard;
import com.educare.model.Student;
import com.educare.model.StudentCourseMap;
import com.educare.service.DashboardService;
import com.educare.service.StudentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	StudentService studentService;
	
	@Autowired
	DashboardService dashboardService;
	
	@PostMapping("/courses")
	public ResponseEntity<List<Course>> getCoursesByStdYear(@RequestBody Standard s){
		
		List<Course> response = dashboardService.getAllCoursesForDashboard(s);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	@PostMapping("/playlist")
	public ResponseEntity<List<Playlist>> getPlaylistByCourse(@RequestBody Course c){
		
		List<Playlist> response = dashboardService.getPlaylistForCourse(c);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PostMapping("/studentDash")
	public ResponseEntity<List<Course>> getStudentDashboard(@RequestBody Student s){
		
		List<Course> response = dashboardService.getCoursesPurchasedByStudent(s);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PostMapping("/purchaseCourse")
	public ResponseEntity<List<Course>> purchaseCourse(@RequestBody StudentCourseMap scm ){
		
		List<Course> response = dashboardService.purchaseCourse(scm);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PostMapping("/enquiry")
	public ResponseEntity<Enquiry> saveEnquiry(@RequestBody Enquiry e){
		
		Enquiry result = dashboardService.saveEnquiryData(e);
		
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@PostMapping("/purchaseStatus")
	public ResponseEntity<StudentCourseMap> purchaseStatus(@RequestBody StudentCourseMap scm ){
		
		StudentCourseMap response = dashboardService.checkForPurchaseStatus(scm);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
