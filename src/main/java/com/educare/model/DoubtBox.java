package com.educare.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "doubt_box")
@NoArgsConstructor
@Getter
@Setter
public class DoubtBox {

	@Id
	private int id;
	
	private int studentId;
	
	private int courseId;
	
	private String doubtQuest;
	
	private String doubtAns;
	
	
}
