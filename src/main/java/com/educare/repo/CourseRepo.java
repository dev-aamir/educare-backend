package com.educare.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educare.model.Course;
import com.educare.model.Playlist;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {

	List<Course> findAllByCourseStdId(int stdYear);
	
	List<Course> findAllByCourseType(String courseType);

	List<Course> findAllByCourseTeacherId(Long teacherId);
}
