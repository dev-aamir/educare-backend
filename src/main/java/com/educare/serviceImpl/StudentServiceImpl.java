package com.educare.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educare.model.Student;
import com.educare.repo.StudentRepo;
import com.educare.service.StudentService;
import com.educare.utility.Validator;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepo studentRepo;
	
	@Autowired
	Validator validator;
	
	@Override
	public Student createStudentAccount(Student s) {
		
		Map<String,String> validatorResponse = validator.validateStudent(s);
		
		if(!validatorResponse.isEmpty()) {
			s.setValidationResponse(validatorResponse);
		}else {
			Student student = studentRepo.save(s);
			return student;
		}
		
		return null;
		
	}

	@Override
	public Student loginStudent(Student s) {
		if(!s.getStudentUsername().isEmpty() && !s.getStudentPassword().isEmpty()) {
			return studentRepo.findByStudentUsernameAndStudentPassword(
					s.getStudentUsername(), s.getStudentPassword());
		}
		
		return null;
	}

	@Override
	public List<Student> findAll() {
		return studentRepo.findAll();
	}

}
