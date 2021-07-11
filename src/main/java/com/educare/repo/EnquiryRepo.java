package com.educare.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educare.model.Enquiry;

@Repository
public interface EnquiryRepo extends JpaRepository<Enquiry, Integer> {

	
}
