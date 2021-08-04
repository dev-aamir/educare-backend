package com.educare.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educare.model.Quiz;
import com.educare.model.QuizQuestions;
import com.educare.model.QuizResult;
import com.educare.repo.QuizQuestionRepo;
import com.educare.repo.QuizRepo;
import com.educare.repo.QuizResultRepo;
import com.educare.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {
	
	private static final Logger LOGGER = LogManager.getLogger();

	@Autowired
	QuizQuestionRepo quizRepo;
	
	@Autowired
	QuizResultRepo resultRepo;
	
	@Autowired
	QuizRepo quizsRepo;

	@Override
	public List<QuizQuestions> getQuestionsByQuiz(Quiz q) {
		if(q != null && q.getQuizId() != 0) {
			
			List<QuizQuestions> response =  quizRepo.findByQuestQuizId(q.getQuizId());
			
			List<QuizQuestions> outputList = new ArrayList<>();
			
			for(QuizQuestions quest : response) {
				
				QuizQuestions q1 = new QuizQuestions();
				
				q1.setQuestId(quest.getQuestId());
				q1.setQuestQuizId(quest.getQuestQuizId());
				q1.setQuestion(quest.getQuestion());
				q1.setOption1(quest.getOption1());
				q1.setOption2(quest.getOption2());
				q1.setOption3(quest.getOption3());
				q1.setOption4(quest.getOption4());
				
				outputList.add(q1);
			}
			
			return outputList;
			
		}
		return null;
	}

	@Override
	public QuizResult checkQuizAnswers(List<QuizQuestions> answersList) {
		
		List<QuizQuestions> submittedList = answersList;
		
		QuizResult result = new QuizResult();
		
		int correctCount = 0;
		
		if(!submittedList.isEmpty()) {
			int quizId = submittedList.get(0).getQuestQuizId();
			
			List<QuizQuestions> questionsList =  quizRepo.findByQuestQuizId(quizId);
			
			for(int i=0; i < questionsList.size(); i++) {
				for(int j=0; j < submittedList.size(); j++) {
					if(submittedList.get(j).getQuestId() == questionsList.get(i).getQuestId()) {
						
						submittedList.get(j).setQuestion(questionsList.get(i).getQuestion());
						submittedList.get(j).setOption1(questionsList.get(i).getOption1());
						submittedList.get(j).setOption2(questionsList.get(i).getOption2());
						submittedList.get(j).setOption3(questionsList.get(i).getOption3());
						submittedList.get(j).setOption4(questionsList.get(i).getOption4());
						
						if(submittedList.get(j).getAnswerSubmitted() == questionsList.get(i).getAnswer()) {
							submittedList.get(j).setAnswerCorrect(true);
							correctCount++;
						}else {
							submittedList.get(j).setAnswerCorrect(false);
						}
					}
				}
			}
			
			result.setQuizId(quizId);
			
			if(correctCount >= questionsList.size() * 0.8 ) {
				result.setQuizRemarks("Pass : "+((correctCount * 100 ) / questionsList.size())+"%");
			}else {
				result.setQuizRemarks("Fail : "+((correctCount * 100 ) / questionsList.size())+"%");
			}
			
			result.setQuizResponse(submittedList);
			
			result.setQuizScore(correctCount);
			
			result.setStudId(submittedList.get(0).getStudentId());
			
			LOGGER.info(submittedList.get(0).getTimeTaken());
			
			result.setQuizTimeTaken(submittedList.get(submittedList.size()-1).getTimeTaken());
			
			resultRepo.save(result);
			
			return result;
		}
		
		
		return null;
	}

	@Override
	public List<Quiz> getQuizList(int studentStandard) {
		// TODO Auto-generated method stub
		
		return quizsRepo.findByQuizStandard(studentStandard);
	}
	
	
	
}
