package com.educare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="mails")
public class Mail {

	 @Id
	 @Column(name="id")
	 private Long id;
	
	 @Column(name="mail_name")
	 private String mailName;
	 
	 @Column(name="mail_subject")
	 private String mailSubject;
	
	 @Column(name="mail_body")
	 private String mailBody;
	
}
