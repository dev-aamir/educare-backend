package com.educare.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educare.model.CourseDetails;

@Repository
public interface CourseDetailsRepo extends JpaRepository<CourseDetails, Integer> {

	List<CourseDetails> findAllByCdCourseId(int courseId);
		
}
