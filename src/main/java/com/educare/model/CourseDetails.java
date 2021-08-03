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
@Table(name="course_details")
public class CourseDetails {

	@Id
	@Column(name="id")
	private int cdId;
	
	@Column(name="cd_course_id")
	private int cdCourseId;
	
	@Column(name="cd_course_name")
	private String cdCourseName;
	
	@Column(name="cd_course_info")
	private String cdCourseInfo;
	
	@Column(name="cd_course_chapter")
	private String cdCourseChapter;
	
	@Column(name="cd_course_ch_info")
	private String cdCourseChapterInfo;
	
	@Column(name="cd_course_prof_name")
	private String cdCourseProfName;

	@Column(name="cd_active")
	private boolean cdActive;
	
	@Column(name="cd_course_snap")
	private String cdCourseSnap;
	
	@Column(name="cd_course_price")
	private int cdCoursePrice;
	
	@Column(name="cd_course_duration")
	private int cdCourseDuration;
	
	
}
