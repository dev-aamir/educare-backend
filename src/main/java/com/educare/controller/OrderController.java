package com.educare.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educare.config.RazorPayClientConfig;
import com.educare.model.OrderRequest;
import com.educare.model.OrderResponse;
import com.educare.model.PayFailed;
import com.educare.model.PaymentResponse;
import com.educare.model.StudentCourseMap;
import com.educare.service.DashboardService;
import com.educare.service.OrderService;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/order")
public class OrderController {
	private RazorpayClient client;
	 
    private RazorPayClientConfig razorPayClientConfig;
 
    @Autowired
    private OrderService orderService;
    
    @Autowired
    public OrderController(RazorPayClientConfig razorpayClientConfig) throws RazorpayException {
        this.razorPayClientConfig = razorpayClientConfig;
        this.client = new RazorpayClient(razorpayClientConfig.getKey(), razorpayClientConfig.getSecret());
    }
 
    @CrossOrigin(origins = "*")
    @PostMapping("/order")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        OrderResponse razorPay = null;
        try {
            // The transaction amount is expressed in the currency subunit, such
            // as paise (in case of INR)
            String amountInPaise = convertRupeeToPaise(orderRequest.getAmount());
            // Create an order in RazorPay and get the order id
            com.razorpay.Order order = createRazorPayOrder(amountInPaise);
            razorPay = getOrderResponse((String) order.get("id"), amountInPaise);
            // Save order in the database
            orderService.saveOrder(razorPay.getRazorpayOrderId(), Long.valueOf(orderRequest.getUserId()) , orderRequest.getCourseId());
        } catch (RazorpayException e) {
            log.error("Exception while create payment order", e);
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok(razorPay);
    }
 
    @CrossOrigin(origins = "*")
    @PutMapping("/order")
    public ResponseEntity<String> updateOrder(@RequestBody PaymentResponse paymentResponse) {
        String errorMsg = orderService.validateAndUpdateOrder(paymentResponse.getRazorpayOrderId(), paymentResponse.getRazorpayPaymentId(), paymentResponse.getRazorpaySignature(),
                razorPayClientConfig.getSecret(),paymentResponse.getStatus());
        if (errorMsg != null) {
            return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Payment Success");
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping("/failedPayment")
    public ResponseEntity<PayFailed> payFailed(@RequestBody PayFailed paymentErr) {
    	
    	if (paymentErr != null) {
    		PayFailed res = orderService.savePayementFailedStatus(paymentErr);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
 
    private OrderResponse getOrderResponse(String orderId, String amountInPaise) {
        OrderResponse razorPay = new OrderResponse();
        razorPay.setApplicationFee(amountInPaise);
        razorPay.setRazorpayOrderId(orderId);
        razorPay.setSecretKey(razorPayClientConfig.getKey());
        return razorPay;
    }
 
    private com.razorpay.Order createRazorPayOrder(String amount) throws RazorpayException {
        JSONObject options = new JSONObject();
        options.put("amount", amount);
        options.put("currency", "INR");
        options.put("receipt", "txn_123456");
        return client.Orders.create(options);
    }
 
    private String convertRupeeToPaise(String paise) {
        BigDecimal b = new BigDecimal(paise);
        BigDecimal value = b.multiply(new BigDecimal("100"));
        return value.setScale(0, RoundingMode.UP).toString();
    }
}
