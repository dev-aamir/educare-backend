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
@Table(name="student_course_map")
public class StudentCourseMap {

	@Id
	@Column(name="scm_id")
	private int mapId;
	
	@Column(name="scm_stu_id")
	private int studentId;
	
	@Column(name="scm_course_id")
	private int courseId;
	
	@Column(name="scm_active")
	private boolean active;
}
