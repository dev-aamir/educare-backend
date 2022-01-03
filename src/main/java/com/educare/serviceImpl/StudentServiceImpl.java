package com.educare.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educare.model.DoubtBox;
import com.educare.model.Mail;
import com.educare.model.SessionDumper;
import com.educare.model.Student;
import com.educare.repo.DoubtRepo;
import com.educare.repo.MailRepo;
import com.educare.repo.SessionRepo;
import com.educare.repo.StudentRepo;
import com.educare.service.StudentService;
import com.educare.utility.MailerUtility;
import com.educare.utility.Validator;

@Service
public class StudentServiceImpl implements StudentService {
	
	private static final Logger LOGGER = LogManager.getLogger();

	@Autowired
	StudentRepo studentRepo;
	
	@Autowired
	Validator validator;
	
	@Autowired
	DoubtRepo dbRepo;
	
	@Autowired
	SessionRepo sessionRepo;
	
	@Autowired
	MailRepo mailRepo;
	
	@Autowired
	MailerUtility mailer;
	
	@Override
	public Student createStudentAccount(Student s) {
		
		Map<String,String> validatorResponse = validator.validateStudent(s);
		
		if(!validatorResponse.isEmpty()) {
			s.setValidationResponse(validatorResponse);
		}else {
			List<Student> existingStudents = studentRepo.findByStudentEmailOrStudentMobileOrStudentUsername(s.getStudentEmail(),s.getStudentMobile(),s.getStudentUsername());
			if(existingStudents.isEmpty()) {
				Student student = studentRepo.save(s);
				
				try {
					//send welcome mail
					Mail mailContent = new Mail();
					mailContent.setMailSubject("Welcome to Hayat Educare!");
					mailContent.setMailBody("Hi <strong>"+s.getStudentFirstName()+"</strong>,<br> I <strong>Ajaj Shaikh, CEO</strong> of Hayat Educare welcome you to "
							+ "your wonderful journey of learning. Hope you will have a great learning ahead.<br>"
							+ "Your credentials are :<br>"
							+ "Username :" + s.getStudentUsername() + " <br>"
							+ "Password :" + s.getStudentPassword());
					
					mailer.sendHtmlMail(mailContent,s.getStudentEmail());
					
				}catch(Exception e) {
					LOGGER.error(e);
					LOGGER.info("Welcome mail send failed");
				}
				
				student.setMessage("SUS000");
				return student;
			}else {
				Student res = new Student();
				res.setMessage("SUE000");
				return res;
			}
			
		}
		
		return null;
		
	}

	@Override
	public Student loginStudent(Student s) {
		if(!s.getStudentUsername().isEmpty() && !s.getStudentPassword().isEmpty()) {
			Student response =  studentRepo.findByStudentUsernameAndStudentPassword(
					s.getStudentUsername(), s.getStudentPassword());
			if(response != null) {
				
				SessionDumper sessionCheck = sessionRepo.findBySessionUserIdAndSessionStatus(response.getStudentId(),true);
				
				if(sessionCheck != null) {
					Student stu =  new Student();
					stu.setMessage("LE000");
					return stu;
				}
				
				Random r = new Random();
				int randomInt = r.nextInt(100) + 1;
				
				SessionDumper sd = new SessionDumper();
				sd.setSessionUserId(response.getStudentId());
				sd.setSessionStatus(true);
				sd.setSessionKey("HE"+response.getStudentId()+"A"+randomInt);
				
				sessionRepo.save(sd);
				
				response.setSessionKey("HE"+response.getStudentId()+"A"+randomInt);
				
				if(response.getStudentEmail().contains("adminhayat.com")) {
					response.setAdmin(true);
				}
				
				return response;
			}
		}
		
		return null;
	}

	@Override
	public List<Student> findAll() {
		return studentRepo.findAll();
	}

	@Override
	public DoubtBox saveDoubt(DoubtBox db) {
		return dbRepo.save(db);
	}

	@Override
	public List<DoubtBox> fetchAllDoubts(Student s) {
		
		return dbRepo.findByStudentId(s.getStudentId());
	}

	@Override
	public void logoutStudent(Student s) {
		
		SessionDumper sd = sessionRepo.findBySessionUserIdAndSessionKey(s.getStudentId(), s.getSessionKey());
		
		if(sd != null) {
			sd.setSessionStatus(false);
			sessionRepo.save(sd);
		}else {
			//Invalid Session Found
			//Has to be done something for this;
		}
		
	}

	@Override
	public void globalLogout(Student s) {
		Student response =  studentRepo.findByStudentUsernameAndStudentPassword(
				s.getStudentUsername(), s.getStudentPassword());
		
		List<SessionDumper> sessionList = sessionRepo.findBySessionUserId(response.getStudentId());
		
		if(!sessionList.isEmpty()) {
			for(SessionDumper sd : sessionList) {
				if(sd.isSessionStatus() == true) {
					sd.setSessionStatus(false);
					sessionRepo.save(sd);
				}
			}
		}
		
	}

	@Override
	public String getCurrentActiveSessionKey(Student s) {
		
		SessionDumper sd = sessionRepo.findBySessionUserIdAndSessionStatus(s.getStudentId(), true);
		
		if(sd != null) {
			return sd.getSessionKey();
		}
		
		return null;
	}

}
