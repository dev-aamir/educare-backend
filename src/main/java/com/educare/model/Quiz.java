package com.educare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="quizs")
public class Quiz {

	@Id
	@Column(name="id")
	private int quizId;
	
	@Column(name="quiz_name")
	private String quizName;
	
	@Column(name="quiz_sub")
	private String quizSubject;
	
	@Column(name="quiz_std")
	private int quizStandard;
	
	@Column(name="quiz_active")
	private boolean active;
	
}
