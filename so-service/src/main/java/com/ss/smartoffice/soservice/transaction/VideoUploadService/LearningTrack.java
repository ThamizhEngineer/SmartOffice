package com.ss.smartoffice.soservice.transaction.VideoUploadService;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="t_learning_track")

@Scope("prototype")
public class LearningTrack {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String videoName;
	private String videoUrl;
	private String videoUploadId;
	private String videoUploadCategory;
	private String videoUploadDesc;
	private String videoUploadDuration;
	private String videoUploadEmployeeId;
	private String isLike;
	private String isDisLike;
	private String comment;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="t_learning_track_id")
	private List<LearningTrackDetails>learningTrackDetails;
	public LearningTrack() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LearningTrack(Integer id, String videoName, String videoUrl, String videoUploadId,
			String videoUploadCategory, String videoUploadDesc, String videoUploadDuration,
			String videoUploadEmployeeId, String isLike, String isDisLike, String comment, String isEnabled,
			String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt,
			List<LearningTrackDetails> learningTrackDetails) {
		super();
		this.id = id;
		this.videoName = videoName;
		this.videoUrl = videoUrl;
		this.videoUploadId = videoUploadId;
		this.videoUploadCategory = videoUploadCategory;
		this.videoUploadDesc = videoUploadDesc;
		this.videoUploadDuration = videoUploadDuration;
		this.videoUploadEmployeeId = videoUploadEmployeeId;
		this.isLike = isLike;
		this.isDisLike = isDisLike;
		this.comment = comment;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.learningTrackDetails = learningTrackDetails;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getVideoUploadId() {
		return videoUploadId;
	}
	public void setVideoUploadId(String videoUploadId) {
		this.videoUploadId = videoUploadId;
	}
	public String getVideoUploadCategory() {
		return videoUploadCategory;
	}
	public void setVideoUploadCategory(String videoUploadCategory) {
		this.videoUploadCategory = videoUploadCategory;
	}
	public String getVideoUploadDesc() {
		return videoUploadDesc;
	}
	public void setVideoUploadDesc(String videoUploadDesc) {
		this.videoUploadDesc = videoUploadDesc;
	}
	public String getVideoUploadDuration() {
		return videoUploadDuration;
	}
	public void setVideoUploadDuration(String videoUploadDuration) {
		this.videoUploadDuration = videoUploadDuration;
	}
	public String getVideoUploadEmployeeId() {
		return videoUploadEmployeeId;
	}
	public void setVideoUploadEmployeeId(String videoUploadEmployeeId) {
		this.videoUploadEmployeeId = videoUploadEmployeeId;
	}
	public String getIsLike() {
		return isLike;
	}
	public void setIsLike(String isLike) {
		this.isLike = isLike;
	}
	public String getIsDisLike() {
		return isDisLike;
	}
	public void setIsDisLike(String isDisLike) {
		this.isDisLike = isDisLike;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
	public List<LearningTrackDetails> getLearningTrackDetails() {
		return learningTrackDetails;
	}
	public void setLearningTrackDetails(List<LearningTrackDetails> learningTrackDetails) {
		this.learningTrackDetails = learningTrackDetails;
	}
	@Override
	public String toString() {
		return "LearningTrack [id=" + id + ", videoName=" + videoName + ", videoUrl=" + videoUrl + ", videoUploadId="
				+ videoUploadId + ", videoUploadCategory=" + videoUploadCategory + ", videoUploadDesc="
				+ videoUploadDesc + ", videoUploadDuration=" + videoUploadDuration + ", videoUploadEmployeeId="
				+ videoUploadEmployeeId + ", isLike=" + isLike + ", isDisLike=" + isDisLike + ", comment=" + comment
				+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + ", learningTrackDetails="
				+ learningTrackDetails + "]";
	}
	
}
