package com.educare.model;

import lombok.Data;

@Data
public class OrderRequest {
	private String customerName;
    private String email;
    private String phoneNumber;
    private String amount;
    private int userId;
    private int courseId;
}
