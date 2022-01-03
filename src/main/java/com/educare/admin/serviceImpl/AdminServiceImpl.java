package com.educare.admin.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.educare.admin.model.RequestModel;
import com.educare.admin.model.Teacher;
import com.educare.admin.model.TeacherMap;
import com.educare.admin.repo.TeacherMapRepo;
import com.educare.admin.repo.TeacherRepo;
import com.educare.admin.service.AdminService;
import com.educare.model.Course;
import com.educare.model.DoubtBox;
import com.educare.model.Student;
import com.educare.repo.CourseRepo;
import com.educare.repo.DoubtRepo;
import com.educare.repo.StudentRepo;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	@Autowired
	TeacherRepo teacherRepo;
	
	@Autowired
	TeacherMapRepo teacherMapRepo;
	
	@Autowired
	StudentRepo studentRepo;
	
	@Autowired
	CourseRepo courseRepo;
	
	@Autowired
	DoubtRepo doubtRepo;

	
	
	@Override
	public Teacher addTeacherAccount(Teacher t) {
		
		if(t != null) {
			return teacherRepo.save(t);
		}
		
		return null;
	}


	@Override
	public String mapTeacher(TeacherMap tm) {
		
		if(tm != null) {
			TeacherMap response =  teacherMapRepo.save(tm);
			
			if(response.getId() != null) {
				return "TM001"; //Teacher Map Success 
			}
		}
		
		return "TM999"; //Teacher Map Failed 
	}


	@Override
	public Teacher login(Teacher t) {
		Teacher tres = new Teacher();
		
		if(!t.getTeacherUsername().isEmpty() && !t.getTeacherPassword().isEmpty()) {
			Teacher response = teacherRepo.findByTeacherUsernameAndTeacherPassword(t.getTeacherUsername(), t.getTeacherPassword());
			
			if(response!= null && response.isTeacherActive()) {
				
				tres.setTeacherMobile(response.getTeacherMobile());
				tres.setTeacherFName(response.getTeacherFName());
				tres.setTeacherLName(response.getTeacherLName());
				tres.setTeacherEmail(response.getTeacherEmail());
				tres.setTeacherGender(response.getTeacherGender());
				tres.setTeacherExp(response.getTeacherExp());
				tres.setAdmin(response.isAdmin());
				tres.setTeacherId(response.getTeacherId());
				tres.setTeacherActive(response.isTeacherActive());
				tres.setSessionKey("HETL"+(response.getTeacherId()+100)+"H"+(new Random().nextInt(500 - 100 + 1) + 100));
				tres.setMessage("TL001"); //Login Success
				
				
				//Fetching all subjects of Teacher
				/*if(response.isAdmin() == false) {
					tres.setTmap(teacherMapRepo.findAllByTeacherId(response.getTeacherId()));
				}*/
				
				
				return tres;
			}else {
				
				tres.setMessage("TL002"); //Login success but teacher not active
				return tres;
			}
			
			
		}
		
		tres.setMessage("TL999"); //Login Failed
		return tres;
	}


	@Override
	public List<Student> showAllStudents(RequestModel req) {
		
		List<Student> studentList = new ArrayList<>();
		
		if(req != null) {
			String key = req.getRequestKey();
			
			if(validateRequestKey(key)) {
				
				List<Student> responseList = studentRepo.findAll();
				
				if(!responseList.isEmpty()) {
					
					for(Student s : responseList) {
						
						Student student = new Student();
						student.setStudentFirstName(s.getStudentFirstName());
						student.setStudentLastName(s.getStudentLastName());
						student.setStudentEmail(s.getStudentEmail());
						student.setStudentMobile(s.getStudentMobile());
						student.setStudentGender(s.getStudentGender());
						student.setStudentId(s.getStudentId());
						student.setStudentActive(s.isStudentActive());
						
						studentList.add(student);
						
					}
					
				}
			}
		}
		
		return studentList;
	}
	
	
	@Override
	public List<Teacher> showAllTeachers(RequestModel req) {
		
		List<Teacher> teachersList = new ArrayList<>();
		
		if(req != null) {
			String key = req.getRequestKey();
			
			if(validateRequestKey(key)) {
				
				List<Teacher> responseList = teacherRepo.findAll();
				
				if(!responseList.isEmpty()) {
					
					for(Teacher response : responseList) {
						
						Teacher tres = new Teacher();
						tres.setTeacherMobile(response.getTeacherMobile());
						tres.setTeacherFName(response.getTeacherFName());
						tres.setTeacherLName(response.getTeacherLName());
						tres.setTeacherEmail(response.getTeacherEmail());
						tres.setTeacherGender(response.getTeacherGender());
						tres.setTeacherExp(response.getTeacherExp());
						tres.setAdmin(response.isAdmin());
						tres.setTeacherId(response.getTeacherId());
						tres.setTeacherActive(response.isTeacherActive());
						
						teachersList.add(tres);
						
					}
					
				}
			}
		}
		
		return teachersList;
	}
	
	@Override
	public List<Course> showAllCourses(RequestModel req) {
		
		List<Course> courseList = new ArrayList<>();
		
		if(req != null) {
			String key = req.getRequestKey();
			
			if(validateRequestKey(key)) {
				
				courseList = courseRepo.findAll();
			}
		}
		
		return courseList;
	}
	
	@Override
	public List<Course> showCoursesByTeacherId(Teacher t) {
		
		List<Course> courseList = new ArrayList<>();
		
		if(t.getMessage().equalsIgnoreCase("HASCTX001")) {
			courseList = courseRepo.findAllByCourseTeacherId(t.getTeacherId());
		}
		
		return courseList;
	}
	
	@Override
	public String addStudent(Student s) {
		
		if(s.getMessage().equalsIgnoreCase("HAADDSTUDX001")) {
			
			Student res = studentRepo.save(s);
			
			if(res != null) {
				return "HAASREQ001"; //Add Student Success
			}
		}
		
		return "HAASREQ999"; //Add Student Failed
	}
	
	
	@Override
	public String deactivateStudent(Student s) {
		
		if(s.getMessage().equalsIgnoreCase("HADEACTSTUDX001")) {
			
			Optional<Student> res = studentRepo.findById(s.getStudentId());
			
			if(res.isPresent()) {
				
				Student student = res.get();
				student.setStudentActive(false);
				
				studentRepo.save(student);
				
				return "HADSREQ001"; //Deactivated Student Success
			
			}else {
				
				return "HADSREQ002"; //No Student Found 
			}
			
		}
		
		return "HADSREQ999"; //De-active Student Req Failed
		
	}
	
	
	@Override
	public String updateStudent(Student s) {
		
		if(s.getMessage().equalsIgnoreCase("HAUPSTUDX001")) {
			
			Optional<Student> res = studentRepo.findById(s.getStudentId());
			
			if(res.isPresent()) {
				
				Student student = res.get();
				student.setStudentEmail(s.getStudentEmail());
				student.setStudentMobile(s.getStudentMobile());
				
				studentRepo.save(student);
				
				return "HAUSREQ001"; //Update Student Success
			
			}else {
				
				return "HAUSREQ002"; //No Student Found 
			}
			
		}
		
		return "HAUSREQ999"; //Update Student Req Failed
		
	}
	
	
	@Override
	public String addCourse(Course c) {
		
		if(c.getMessage().equalsIgnoreCase("HAADDCRSX001")) {
			
			Course res = courseRepo.save(c);
			
			if(res != null) {
				return "HAACREQ001"; //Add Course Success
			}
		}
		
		return "HAACREQ999"; //Add Course Failed
	}
	
	
	
	
	private boolean validateRequestKey(String key) {
		
		//HA$9000RZSAS
		
		if(key.startsWith("HA")) {
			
			int keyCode = Integer.valueOf(key.substring(3, 7));
			int keyModular = key.substring(key.lastIndexOf('Z')).length();
			
			
			int value = keyCode % keyModular;
			
			if(value == 0) {
				return true;
			}
		}
		
		return false;
	}


	@Override
	public List<DoubtBox> showAllDoubts() {
		List<DoubtBox> doubtsList = doubtRepo.findAll();
		List<DoubtBox> doubtsListResponse = new ArrayList<>(); 
		for(DoubtBox d : doubtsList) {
			Student s = studentRepo.findByStudentId(d.getStudentId());
			
			if(s.getStudentId() >0) {
				d.setStuFname(s.getStudentFirstName());
			}
			
			Course c = courseRepo.findById(d.getCourseId()).get();
			
			if(c.getCourseId() > 0) {
				d.setCName(c.getCourseName());
			}
			
			
			doubtsListResponse.add(d);
			
		}
		return doubtsListResponse;
	}


	@Override
	public DoubtBox saveDoubtAnswer(DoubtBox d) {
		
		if(doubtRepo.findById(d.getId()).isPresent()) {
			int res = doubtRepo.updateAnswer(d.getDoubtAns(), d.getId());
			
			if(res > 0) {
				d.setMessage("DB001");
			}else {
				d.setMessage("DB000");
			}
			
		}else {
			d.setMessage("DB000");
		}
		return d;
	}


	

	
	
	
}
