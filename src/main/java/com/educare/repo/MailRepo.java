package com.educare.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educare.model.Mail;

@Repository
public interface MailRepo extends JpaRepository<Mail, Integer> {

	Mail findMailByMailName(String name);
}
