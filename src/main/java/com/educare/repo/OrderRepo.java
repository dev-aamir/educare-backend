package com.educare.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educare.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
 
    Order findByRazorpayOrderId(String orderId);

}
