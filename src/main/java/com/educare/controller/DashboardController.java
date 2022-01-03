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
import com.educare.model.CourseDetails;
import com.educare.model.Enquiry;
import com.educare.model.Mail;
import com.educare.model.Notes;
import com.educare.model.Playlist;
import com.educare.model.Standard;
import com.educare.model.Student;
import com.educare.model.StudentCourseMap;
import com.educare.repo.MailRepo;
import com.educare.service.DashboardService;
import com.educare.service.StudentService;
import com.educare.utility.MailerUtility;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	StudentService studentService;
	
	@Autowired
	DashboardService dashboardService;
	
	@Autowired
	MailerUtility mailer;
	
	@Autowired
	MailRepo mailRepo;
	
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
		
		//send Thanks for enquiry mail
		Mail mailContent = mailRepo.findMailByMailName("enquiry");
		mailer.sendEmail(mailContent,e.getEnquiryEmail());
		
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@PostMapping("/purchaseStatus")
	public ResponseEntity<StudentCourseMap> purchaseStatus(@RequestBody StudentCourseMap scm ){
		
		StudentCourseMap response = dashboardService.checkForPurchaseStatus(scm);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PostMapping("/courseDetails")
	public ResponseEntity<List<CourseDetails>> fetchCourseDetails(@RequestBody Course c ){
		
		List<CourseDetails> cdResponse = dashboardService.fetchCourseDetails(c.getCourseId());
		
		return new ResponseEntity<>(cdResponse,HttpStatus.OK);
	}
	
	@PostMapping("/showCourses")
	public ResponseEntity<List<Course>> getCourses(@RequestBody Course c){
		
		List<Course> response = dashboardService.getAllCoursesForShowcase(c);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PostMapping("/getNotes")
	public ResponseEntity<List<Notes>> getNotes(@RequestBody Course c){
		
		List<Notes> response = dashboardService.getAllNotesByCourseId(c.getCourseId());
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
