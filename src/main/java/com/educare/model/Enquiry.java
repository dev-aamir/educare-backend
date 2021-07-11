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
@Table(name="enquiry")
public class Enquiry {

	@Id
	@Column(name="enq_id")
	private int enquiryId;
	
	@Column(name="enq_name")
	private String enquiryName;
	
	@Column(name="enq_mobile")
	private String enquiryMobile;
	
	@Column(name="enq_email")
	private String enquiryEmail;
	
	@Column(name="enq_msg")
	private String enquiryMessage;
	
	
}
