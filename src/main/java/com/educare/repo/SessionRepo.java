package com.educare.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educare.model.SessionDumper;

@Repository
public interface SessionRepo extends JpaRepository<SessionDumper, Integer> {

	SessionDumper findBySessionUserIdAndSessionStatus(int studentId, boolean status);
	SessionDumper findBySessionUserIdAndSessionKey(int studentId, String sessionKey);
	List<SessionDumper> findBySessionUserId(int userId);

}
