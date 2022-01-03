package com.educare.service;

import java.util.List;

import com.educare.model.DoubtBox;
import com.educare.model.Student;

public interface StudentService {

	Student createStudentAccount(Student s);
	
	Student loginStudent(Student s);
	
	List<Student> findAll();
	
	DoubtBox saveDoubt(DoubtBox db);
	
	List<DoubtBox> fetchAllDoubts(Student s);
	
	void logoutStudent(Student s);
	
	void globalLogout(Student s);
	
	String getCurrentActiveSessionKey(Student s);
}
