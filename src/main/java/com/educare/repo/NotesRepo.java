package com.educare.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educare.model.Notes;

@Repository
public interface NotesRepo extends JpaRepository<Notes, Integer> {

	List<Notes> findAllByCourseId(int courseId);
	
}
