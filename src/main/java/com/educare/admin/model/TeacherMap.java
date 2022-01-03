package com.educare.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="teacher_map")
public class TeacherMap {

	 @Id
	 @Column(name="id")
	 private Long id;
	
	 @Column(name="ss_teacher_id")
	 private int teacherId;
	 
	 @Column(name="ss_std_id")
	 private int standardId;
	 
	 @Column(name="ss_sub_id")
	 private int subjectId;
	
	 @Column(name="ss_active")
	 private boolean active;
	
}
