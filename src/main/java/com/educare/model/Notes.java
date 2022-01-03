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
@Table(name="notes")
public class Notes {

	@Id
	@Column(name="id")
	private int notesId;
	
	@Column(name="course_id")
	private int courseId;
	
	@Column(name="chapter_name")
	private String chapterName;
	
	@Column(name="chapter_notes")
	private String ChapterNotes;
	
	@Column(name="quiz")
	private int quizNumber;

	@Column(name="active")
	private boolean active;
	
}
