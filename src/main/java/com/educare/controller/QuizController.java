package com.educare.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educare.model.Quiz;
import com.educare.model.QuizQuestions;
import com.educare.model.QuizResult;
import com.educare.model.Student;
import com.educare.service.QuizService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/quiz")
public class QuizController {

	private static final Logger LOGGER = LogManager.getLogger();
	
	@Autowired
	QuizService quizService;

	@PostMapping("/getQuizes")
	public ResponseEntity<List<Quiz>> getQuizes(@RequestBody Student student){
		
		LOGGER.info("Get Quizes Service Hit >>>>>>>");
		
		List<Quiz> quizList = quizService.getQuizList(student.getStudentStandard());
		
		return new ResponseEntity<>(quizList,HttpStatus.OK);
	}
	
	
	@PostMapping("/getQuestions")
	public ResponseEntity<List<QuizQuestions>> getQuizQuestions(@RequestBody Quiz quiz){
		
		LOGGER.info("Get Questions Service Hit >>>>>>>");
		
		List<QuizQuestions> questionsList = quizService.getQuestionsByQuiz(quiz);
		
		return new ResponseEntity<>(questionsList,HttpStatus.OK);
	}
	
	
	@PostMapping("/getQuizResult")
	public ResponseEntity<QuizResult> computeResult(@RequestBody List<QuizQuestions> quiz){
		
		LOGGER.info("Get Results Service Hit >>>>>>>");
		
		QuizResult result = quizService.checkQuizAnswers(quiz);
		
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	

}
