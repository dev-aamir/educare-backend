package com.educare.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pay_failed")
@NoArgsConstructor
@Getter
@Setter
public class PayFailed {

	@Id
	private int id;
	
	private String errCode;
	
	private String errSource;
	
	private String errDescp;
	
	private String errStep;
	
	private String errReason;
	
	private String errOrderId;
	
	private String errPaymentId;
	
	private int errUserId;
	
}
