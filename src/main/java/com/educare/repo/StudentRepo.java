package com.educare.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educare.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

	Student findByStudentUsernameAndStudentPassword(String username, String password);

	List<Student> findByStudentEmailOrStudentMobileOrStudentUsername(String studentEmail, String studentMobile, String username);

}
