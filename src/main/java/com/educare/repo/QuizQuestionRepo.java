package com.educare.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educare.model.QuizQuestions;

@Repository
public interface QuizQuestionRepo extends JpaRepository<QuizQuestions, Integer> {

	List<QuizQuestions> findByQuestQuizId(int quizId);
}
