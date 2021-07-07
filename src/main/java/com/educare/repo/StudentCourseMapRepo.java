package com.educare.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educare.model.StudentCourseMap;

@Repository
public interface StudentCourseMapRepo extends JpaRepository<StudentCourseMap, Integer> {

	List<StudentCourseMap> findByStudentId(int studentId);
}
