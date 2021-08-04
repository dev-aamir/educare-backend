package com.educare.model;

import lombok.Data;

@Data
public class PaymentResponse {
	private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;
    private String status;
}