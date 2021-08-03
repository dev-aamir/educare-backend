package com.educare.service;

import com.educare.model.Order;
import com.educare.model.PayFailed;

public interface OrderService {
	
	Order saveOrder(final String razorpayOrderId, final Long userId, final Integer courseId);
	
	String validateAndUpdateOrder(final String razorpayOrderId, final String razorpayPaymentId, final String razorpaySignature, final String secret, final String status);
	
	PayFailed savePayementFailedStatus(PayFailed err);
	
}
