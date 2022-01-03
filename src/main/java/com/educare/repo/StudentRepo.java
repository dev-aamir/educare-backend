package com.educare.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.educare.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

	Student findByStudentUsernameAndStudentPassword(String username, String password);

	List<Student> findByStudentEmailOrStudentMobileOrStudentUsername(String studentEmail, String studentMobile, String username);

	//@Query("select studentFirstName, studentLastName, studentEmail from Student s where s.studentId = :studentId")
	Student findByStudentId(int i);
}
