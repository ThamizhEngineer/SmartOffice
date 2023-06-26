package com.ss.smartoffice.soservice.transaction.VideoUploadService;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="t_learning_track_details")

@Scope("prototype")
public class LearningTrackDetails {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="t_learning_track_id")
	private String tLearningTrackId;
	private String isVideoWatched;
	private String videoPlayHead;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public LearningTrackDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LearningTrackDetails(Integer id, String tLearningTrackId, String isVideoWatched, String videoPlayHead,
			String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.tLearningTrackId = tLearningTrackId;
		this.isVideoWatched = isVideoWatched;
		this.videoPlayHead = videoPlayHead;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String gettLearningTrackId() {
		return tLearningTrackId;
	}
	public void settLearningTrackId(String tLearningTrackId) {
		this.tLearningTrackId = tLearningTrackId;
	}
	public String getIsVideoWatched() {
		return isVideoWatched;
	}
	public void setIsVideoWatched(String isVideoWatched) {
		this.isVideoWatched = isVideoWatched;
	}
	public String getVideoPlayHead() {
		return videoPlayHead;
	}
	public void setVideoPlayHead(String videoPlayHead) {
		this.videoPlayHead = videoPlayHead;
	}
	public String getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public LocalDateTime getCreatedDt() {
		return createdDt;
	}
	public void setCreatedDt(LocalDateTime createdDt) {
		this.createdDt = createdDt;
	}
	public LocalDateTime getModifiedDt() {
		return modifiedDt;
	}
	public void setModifiedDt(LocalDateTime modifiedDt) {
		this.modifiedDt = modifiedDt;
	}
	@Override
	public String toString() {
		return "LearningTrackDetails [id=" + id + ", tLearningTrackId=" + tLearningTrackId + ", isVideoWatched="
				+ isVideoWatched + ", videoPlayHead=" + videoPlayHead + ", isEnabled=" + isEnabled + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt
				+ "]";
	}
	

	
}
