package com.educare.model;

import java.util.Date;
import java.util.List;

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
@Table(name="quiz_res")
public class QuizResult {

	@Id
	@Column(name="id")
	private int quizResId;
	
	@Column(name="quiz_id")
	private int quizId;
	
	@Column(name="stud_id")
	private int studId;
	
	@Column(name="quiz_score")
	private int quizScore;
	
	@Column(name="quiz_time_taken")
	private int quizTimeTaken;
	
	@Column(name="quiz_remark")
	private String quizRemarks;
	
	@Column(name="quiz_tmstmp")
	private Date quizTimeStamp;
	
	@Transient
	private List<QuizQuestions> quizResponse; 
	
}
