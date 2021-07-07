package com.educare.model;

import java.sql.Timestamp;
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
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	@Id
	@Column(name = "stu_id")
	private int studentId;

	@Column(name = "stu_fname")
	private String studentFirstName;

	@Column(name = "stu_lname")
	private String studentLastName;

	@Column(name = "stu_gender")
	private String studentGender;

	@Column(name = "stu_email")
	private String studentEmail;

	@Column(name = "stu_mobile")
	private String studentMobile;

	@Column(name = "stu_age")
	private int studentAge;

	@Column(name = "stu_standard")
	private int studentStandard;

	@Column(name = "stu_username")
	private String studentUsername;

	@Column(name = "stu_password")
	private String studentPassword;

	@Column(name = "stu_otp")
	private int studentOtp;

	@Column(name = "stu_crtd_tmstmp")
	private Timestamp studentCrtdTmstmp;

	@Column(name = "stu_last_update")
	private Timestamp studentLastUpdateTmstmp;

	@Column(name = "stu_active")
	private boolean studentActive;

	@Transient
	private Map<String, String> validationResponse;

}
