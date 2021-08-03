package com.educare.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educare.model.Course;
import com.educare.model.CourseDetails;
import com.educare.model.Enquiry;
import com.educare.model.Playlist;
import com.educare.model.Standard;
import com.educare.model.Student;
import com.educare.model.StudentCourseMap;
import com.educare.repo.CourseDetailsRepo;
import com.educare.repo.CourseRepo;
import com.educare.repo.EnquiryRepo;
import com.educare.repo.PlaylistRepo;
import com.educare.repo.StudentCourseMapRepo;
import com.educare.repo.StudentRepo;
import com.educare.service.DashboardService;
import com.educare.utility.Validator;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	StudentRepo studentRepo;
	
	@Autowired
	Validator validator;
	
	@Autowired
	CourseRepo courseRepo;
	
	@Autowired
	PlaylistRepo playRepo;
	
	@Autowired
	StudentCourseMapRepo scmRepo;
	
	@Autowired
	EnquiryRepo enquiryRepo;
	
	@Autowired
	CourseDetailsRepo detailsRepo;

	@Override
	public List<Course> getAllCoursesForDashboard(Standard std) {
		
		int stdYear = Integer.parseInt(std.getStandardYear());
		if(stdYear == 13) {
			stdYear = 12;
		}
		return courseRepo.findAllByCourseStdId(stdYear);
		
	}

	@Override
	public List<Playlist> getPlaylistForCourse(Course course) {
		
		return playRepo.findAllByPlaylistCourseId(course.getCourseId());
	}

	@Override
	public List<Course> getCoursesPurchasedByStudent(Student student) {
		
		List<StudentCourseMap> scmList = scmRepo.findByStudentId(student.getStudentId());
	
		List<Course> courseList = new ArrayList<Course>();
		
		for(StudentCourseMap scm : scmList) {
			Optional<Course> c = courseRepo.findById(scm.getCourseId());
			if(c.isPresent()) {
				courseList.add(c.get());
			}
		}
		
		return courseList;
	
	}

	@Override
	public Enquiry saveEnquiryData(Enquiry e) {
		Enquiry enq = enquiryRepo.save(e);
		
		if(enq != null) {
			return enq;
		}
		
		return null;
	}
	
	@Override
	public List<Course> purchaseCourse(StudentCourseMap scm) {
		
		scmRepo.save(scm);
		
		Student s = new Student();
		s.setStudentId(scm.getStudentId());
		
		return getCoursesPurchasedByStudent(s);
		
	}

	@Override
	public StudentCourseMap checkForPurchaseStatus(StudentCourseMap scm) {
		
		StudentCourseMap res = scmRepo.findByStudentIdAndCourseId(scm.getStudentId(),scm.getCourseId());
		if(res != null) {
			return res;
		}
		
		return null;
	}

	@Override
	public List<CourseDetails> fetchCourseDetails(int courseId) {
		
		return detailsRepo.findAllByCdCourseId(courseId);
	}

	@Override
	public List<Course> getAllCoursesForShowcase(Course c) {
		String courseType = c.getCourseType();
		return courseRepo.findAllByCourseType(courseType);
	}
}
