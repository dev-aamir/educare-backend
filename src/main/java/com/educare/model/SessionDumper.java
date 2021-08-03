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
@Table(name="session_dumper")
public class SessionDumper {

	@Id
	@Column(name="sd_id")
	private int sessionId;
	
	@Column(name="sd_user_id")
	private int sessionUserId;
	
	@Column(name="sd_status")
	private boolean sessionStatus;
	
	@Column(name="sd_key")
	private String sessionKey;
}
