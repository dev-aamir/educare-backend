package com.educare.service;

import java.util.List;

import com.educare.model.Student;

public interface StudentService {

	Student createStudentAccount(Student s);
	
	Student loginStudent(Student s);
	
	List<Student> findAll();
}
