package com.educare.admin.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educare.admin.model.TeacherMap;

@Repository
public interface TeacherMapRepo extends JpaRepository<TeacherMap, Long> {

	List<TeacherMap> findAllByTeacherId(Long teacherId);
	
}
