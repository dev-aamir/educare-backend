package com.educare.utility;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.educare.model.Student;

@Service
public class Validator {

	public Map<String,String> validateStudent(Student s){
		
		Map<String,String> results = new HashMap<>();
		
		if(s == null) {
			results.put("Object", "Student Object is Empty");
		}
		
		if(s.getStudentEmail().isEmpty()) {
			results.put("Student Email", "Email is Blank");
		}
		
		//other validations to be added
		
		return results;
		
	}
	
}
