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
@Table(name="course")
public class Course {

	@Id
	@Column(name="c_id")
	private int courseId;
	
	@Column(name="c_name")
	private String courseName;
	
	@Column(name="c_desc")
	private String courseDesc;
	
	@Column(name="c_price")
	private int coursePrice;
	
	@Column(name="c_snap")
	private String courseSnap;
	
	@Column(name="c_std_id")
	private int courseStdId;
	
	@Column(name="c_play_id")
	private int coursePlayId;
	
	@Column(name="c_teacher_id")
	private int courseTeacherId;
	
	@Column(name="c_active")
	private boolean courseActive;
	
	@Column(name="c_type")
	private String courseType;
	
	@Transient
	private String message;
	
}
