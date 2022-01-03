package com.educare.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educare.model.Quiz;
import com.educare.model.QuizQuestions;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, Integer> {

	List<Quiz> findByQuizStandard(int std);

	List<Quiz> findByQuizSubject(String message);
}
