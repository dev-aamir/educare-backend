package com.educare.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educare.model.Playlist;

@Repository
public interface PlaylistRepo extends JpaRepository<Playlist, Integer> {

	List<Playlist> findAllByPlaylistCourseId(int courseId);

		
}
