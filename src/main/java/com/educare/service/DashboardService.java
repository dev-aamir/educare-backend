package com.educare.service;

import java.util.List;

import com.educare.model.Course;
import com.educare.model.Playlist;
import com.educare.model.Standard;
import com.educare.model.Student;
import com.educare.model.StudentCourseMap;

public interface DashboardService {

	List<Course> getAllCoursesForDashboard(Standard std);
	
	List<Playlist> getPlaylistForCourse(Course course);
	
	List<Course> getCoursesPurchasedByStudent(Student student);
	
	List<Course> purchaseCourse(StudentCourseMap scm);
	
	
}
