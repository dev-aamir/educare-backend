package com.educare.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educare.model.QuizQuestions;
import com.educare.model.QuizResult;

@Repository
public interface QuizResultRepo extends JpaRepository<QuizResult, Integer> {


}
