package com.educare.admin.model;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teacher")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

	@Id
	@Column(name = "id")
	private Long teacherId;

	@Column(name = "t_first_name")
	private String teacherFName;

	@Column(name = "t_last_name")
	private String teacherLName;
	
	@Column(name = "t_email")
	private String teacherEmail;
	
	@Column(name = "t_mobile")
	private String teacherMobile;
	
	@Column(name = "t_gender")
	private String teacherGender;
	
	@Column(name = "t_experience")
	private int teacherExp;
	
	@Column(name = "t_username")
	private String teacherUsername;
	
	@Column(name = "t_password")
	private String teacherPassword;
	
	@Column(name = "t_super")
	private boolean isAdmin;
	
	@Column(name = "t_active")
	private boolean teacherActive;
	
	@Transient
	private Map<String, String> validationResponse;
	
	@Transient
	private String sessionKey;
	
	@Transient
	private String message;

	@Transient
	private List<TeacherMap> tmap; 
	
}
