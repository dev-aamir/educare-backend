package com.educare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="playlist")
public class Playlist {

	
	@Id
	@Column(name="play_id")
	private int playlistId;
	
	@Column(name="play_course_id")
	private int playlistCourseId;
	
	@Column(name="play_video_url")
	private String playlistVideoLink;
	
	@Column(name="play_snap")
	private String playlistSnapUrl;
	
	@Column(name="play_name")
	private String playlistName;
	
	@Column(name="play_desc")
	private String playlistDesc;
	
	@Column(name="play_active")
	private boolean playlistActive;
	
	
	
}
