package com.educare.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educare.model.DoubtBox;

@Repository
public interface DoubtRepo extends JpaRepository<DoubtBox, Integer> {

	List<DoubtBox> findByStudentId(int studentId);

		
}
