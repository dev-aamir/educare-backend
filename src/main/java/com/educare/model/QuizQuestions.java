package com.educare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="quiz_questions")
public class QuizQuestions {

	@Id
	@Column(name="id")
	private int questId;
	
	@Column(name="quest_quiz_id")
	private int questQuizId;
	
	@Column(name="quiz_quest")
	private String question;
	
	@Column(name="quiz_option1")
	private String option1;
	
	@Column(name="quiz_option2")
	private String option2;
	
	@Column(name="quiz_option3")
	private String option3;
	
	@Column(name="quiz_option4")
	private String option4;
	
	@Column(name="quiz_ans")
	private int answer;
	
	@Transient
	private int answerSubmitted;
	
	@Transient
	private boolean answerCorrect;
	
	@Transient
	private int studentId;
	
	@Transient
	private int timeTaken;
	
}
