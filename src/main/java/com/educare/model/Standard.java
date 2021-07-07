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
@Table(name="standard")
public class Standard {

	@Id
	@Column(name="std_id")
	private int standardId;
	
	@Column(name="std_year")
	private String standardYear;
	
	@Column(name="std_active")
	private boolean standardActive;
}
