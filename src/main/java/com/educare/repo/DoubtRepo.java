package com.educare.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.educare.model.DoubtBox;

@Repository
public interface DoubtRepo extends JpaRepository<DoubtBox, Integer> {

	List<DoubtBox> findByStudentId(int studentId);

	@Modifying
	@Query("update DoubtBox dbox set dbox.doubtAns = :answer where id= :id")
	int updateAnswer(@Param("answer") String answer, @Param("id") int id);
	
	//@Query("select id,studentId,courseId,doubtQuest,doubtAns,stuFname,cName  from DoubtBox d left join Student s on d.studentId = s.studentId left join course c on d.courseId = c.courseId order by d.crtdTmstmp desc")
	//List<DoubtBox> fetchAllDoubts();

		
}
