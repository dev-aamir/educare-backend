package com.educare.service;

import com.educare.model.Student;

public interface StudentService {

	Student createStudentAccount(Student s);
	
	Student loginStudent(Student s);
}
