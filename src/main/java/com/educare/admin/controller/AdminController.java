package com.educare.admin.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educare.admin.model.RequestModel;
import com.educare.admin.model.Teacher;
import com.educare.admin.model.TeacherMap;
import com.educare.admin.service.AdminService;
import com.educare.model.Course;
import com.educare.model.DoubtBox;
import com.educare.model.Student;
import com.educare.repo.DoubtRepo;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {

	private static final Logger LOGGER = LogManager.getLogger();
	
	@Autowired
	AdminService adminService;
	
	@PostMapping("/addTeacher")
	public ResponseEntity<Teacher> addTeacherAccount(@RequestBody Teacher t){
		
		LOGGER.info("Teacher Add Service Hit >>>>>>>");
		Teacher response = adminService.addTeacherAccount(t);
		
		LOGGER.info("Teacher Add Service Response >>>>>>> {}",response);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	@PostMapping("/mapTeacher")
	public ResponseEntity<String> mapTeacherAcc(@RequestBody TeacherMap tm){
		
		LOGGER.info("Teacher Map Service Hit >>>>>>>");
		String response = adminService.mapTeacher(tm);
		
		LOGGER.info("Teacher Map Service Response >>>>>>> {}",response);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	@PostMapping("/superLogin")
	public ResponseEntity<Teacher> superLogin(@RequestBody Teacher t){
		
		LOGGER.info("Teacher Login Service Hit >>>>>>>");
		Teacher response = adminService.login(t);
		
		LOGGER.info("Teacher login Service Response >>>>>>> {}",response);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PostMapping("/getStudents")
	public ResponseEntity<List<Student>> getStudents(@RequestBody RequestModel rm){
		
		LOGGER.info("Admin Get Students Service Hit >>>>>>>");
		List<Student> studentsList = adminService.showAllStudents(rm);
		
		LOGGER.info("Admin Get Students Service Response >>>>>>>");
		return new ResponseEntity<>(studentsList,HttpStatus.OK);
	}
	
	@PostMapping("/getTeachers")
	public ResponseEntity<List<Teacher>> getTeachers(@RequestBody RequestModel rm){
		
		LOGGER.info("Admin Get Teachers Service Hit >>>>>>>");
		List<Teacher> teacherList = adminService.showAllTeachers(rm);
		
		LOGGER.info("Admin Get Teachers Service Response >>>>>>>");
		return new ResponseEntity<>(teacherList,HttpStatus.OK);
	}
	
	@PostMapping("/getCourses")
	public ResponseEntity<List<Course>> getCourses(@RequestBody RequestModel rm){
		
		LOGGER.info("Admin Get Teachers Service Hit >>>>>>>");
		List<Course> courseList = adminService.showAllCourses(rm);
		
		LOGGER.info("Admin Get Teachers Service Response >>>>>>>");
		return new ResponseEntity<>(courseList,HttpStatus.OK);
	}
	
	@PostMapping("/registerStudent")
	public ResponseEntity<String> registerStudent(@RequestBody Student s){
		
		LOGGER.info("Admin Add Student Service Hit >>>>>>>");
		String response = null;
		
		if(s != null) {
			response = adminService.addStudent(s);
		}else {
			response = "HAASREQ999"; //Add Student Failed
		}
		 
		
		LOGGER.info("Admin Add Student Service Response >>>>>>> {}", response);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	@PostMapping("/deactivateStudent")
	public ResponseEntity<String> deactiveStud(@RequestBody Student s){
		
		LOGGER.info("Admin Deactive Student Service Hit >>>>>>>");
		String response = null;
		
		if(s != null) {
			response = adminService.deactivateStudent(s);
		}else {
			response = "HADSREQ999"; //Deactive Student Failed
		}
		 
		
		LOGGER.info("Admin Deactive Student Service Response >>>>>>> {}", response);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	@PostMapping("/updateStudInfo")
	public ResponseEntity<String> updateStudent(@RequestBody Student s){
		
		LOGGER.info("Admin Update Student Service Hit >>>>>>>");
		String response = null;
		
		if(s != null) {
			response = adminService.updateStudent(s);
		}else {
			response = "HAUSREQ999"; //update Student Failed
		}
		 
		
		LOGGER.info("Admin Update Student Service Response >>>>>>> {}", response);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	@PostMapping("/addCourse")
	public ResponseEntity<String> addCourse(@RequestBody Course c){
		
		LOGGER.info("Admin Add Course Service Hit >>>>>>>");
		String response = null;
		
		if(c != null) {
			response = adminService.addCourse(c);
		}else {
			response = "HAACREQ999"; //update Student Failed
		}
		 
		
		LOGGER.info("Admin  Add Course Service Response >>>>>>> {}", response);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PostMapping("/getTeacherCourses")
	public ResponseEntity<List<Course>> getTeacherCourses(@RequestBody Teacher t){
		
		LOGGER.info("Admin Get Teacher Courses Service Hit >>>>>>>");
		if(t!=null) {
			List<Course> courseList = adminService.showCoursesByTeacherId(t);
		
			LOGGER.info("Admin Get Teacher Courses Service Response >>>>>>>");
			return new ResponseEntity<>(courseList,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null,HttpStatus.OK);
	}
	
	@PostMapping("/getAllDoubts")
	public ResponseEntity<List<DoubtBox>> getAllDoubts(@RequestBody Teacher t){
		
		LOGGER.info("Admin Get All Doubts Service Hit >>>>>>>");
		if(t!=null) {
			List<DoubtBox> doubts = adminService.showAllDoubts();
		
			LOGGER.info("Admin Get All Doubts  Service Response >>>>>>>");
			return new ResponseEntity<>(doubts,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null,HttpStatus.OK);
	}
	
	@PostMapping("/saveDoubtAns")
	public ResponseEntity<DoubtBox> saveDoubtAns(@RequestBody DoubtBox d){
		
		LOGGER.info("Admin save Doubts answer Service Hit >>>>>>>");
		if(d!=null) {
			DoubtBox res = adminService.saveDoubtAnswer(d);
			return new ResponseEntity<DoubtBox>(res,HttpStatus.OK);
		}else {
			d = new DoubtBox();
			d.setMessage("DB000");
		}
		
		return new ResponseEntity<DoubtBox>(d,HttpStatus.OK);
	}
	
	
}
