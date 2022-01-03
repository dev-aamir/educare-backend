package com.educare.admin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educare.admin.model.Teacher;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {

	Teacher findByTeacherUsernameAndTeacherPassword(String username, String password);
}
