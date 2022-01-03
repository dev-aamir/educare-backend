package com.educare.admin.service;

import java.util.List;

import com.educare.admin.model.RequestModel;
import com.educare.admin.model.Teacher;
import com.educare.admin.model.TeacherMap;
import com.educare.model.Course;
import com.educare.model.DoubtBox;
import com.educare.model.Student;

public interface AdminService {

	Teacher addTeacherAccount(Teacher t);
	
	String mapTeacher(TeacherMap tm);
	
	Teacher login(Teacher t);
	
	List<Student> showAllStudents(RequestModel req);

	List<Teacher> showAllTeachers(RequestModel req);
	
	List<Course> showAllCourses(RequestModel req);

	String addStudent(Student s);

	String deactivateStudent(Student s);

	String updateStudent(Student s);

	String addCourse(Course c);

	List<Course> showCoursesByTeacherId(Teacher t);
	
	List<DoubtBox> showAllDoubts();
	
	DoubtBox saveDoubtAnswer(DoubtBox d);
}
