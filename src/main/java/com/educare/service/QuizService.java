package com.educare.service;

import java.util.List;

import com.educare.model.Quiz;
import com.educare.model.QuizQuestions;
import com.educare.model.QuizResult;

public interface QuizService {

	List<QuizQuestions> getQuestionsByQuiz(Quiz q);
	
	QuizResult checkQuizAnswers(List<QuizQuestions> answersList);

	List<Quiz> getQuizList(int studentStandard);
	
	
}
