package com.educare.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educare.model.Order;
import com.educare.model.PayFailed;
import com.educare.model.StudentCourseMap;
import com.educare.repo.OrderRepo;
import com.educare.repo.PayFailedRepo;
import com.educare.repo.StudentCourseMapRepo;
import com.educare.service.OrderService;
import com.educare.utility.Signature;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
    private OrderRepo orderRepository;
	
	@Autowired
	private StudentCourseMapRepo scmRepo;
	
	@Autowired
	private PayFailedRepo failedRepo;
 
    @Transactional
    public Order saveOrder(final String razorpayOrderId, final Long userId, final Integer courseId) {
        Order order = new Order();
        order.setRazorpayOrderId(razorpayOrderId);
        order.setUserId(userId);
        order.setCourseId(courseId);
        Order r = orderRepository.save(order);
        log.info("Order saved");
        return r;
    }
 
    @Transactional
    public String validateAndUpdateOrder(final String razorpayOrderId, final String razorpayPaymentId, final String razorpaySignature, final String secret, final String status) {
        String errorMsg = null;
        try {
            Order order = orderRepository.findByRazorpayOrderId(razorpayOrderId);
            // Verify if the razorpay signature matches the generated one to
            // confirm the authenticity of the details returned
            String generatedSignature = Signature.calculateRFC2104HMAC(order.getRazorpayOrderId() + "|" + razorpayPaymentId, secret);
            if (generatedSignature.equals(razorpaySignature)) {
                order.setRazorpayOrderId(razorpayOrderId);
                order.setRazorpayPaymentId(razorpayPaymentId);
                order.setRazorpaySignature(razorpaySignature);
                order.setStatus(status);
                orderRepository.save(order);
                log.info("Order validated");
                
                StudentCourseMap scm = new StudentCourseMap();
                scm.setStudentId(Integer.valueOf(""+order.getUserId()));
                scm.setCourseId(order.getCourseId());
                scm.setActive(true);
                scmRepo.save(scm);
                log.info("Course mapped against user");
                
                
            } else {
                errorMsg = "Payment validation failed: Signature doesn't match";
                order.setStatus("Failed");
                orderRepository.save(order);
            }
        } catch (Exception e) {
            log.error("Payment validation failed", e);
            errorMsg = e.getMessage();
        }
        return errorMsg;
    }

	@Override
	public PayFailed savePayementFailedStatus(PayFailed err) {
		if(err != null) {
			return failedRepo.save(err);
		}
		
		return null;
	}
}
