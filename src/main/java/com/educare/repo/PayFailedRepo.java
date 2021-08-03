package com.educare.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educare.model.PayFailed;

@Repository
public interface PayFailedRepo extends JpaRepository<PayFailed, Integer> {
		
}
