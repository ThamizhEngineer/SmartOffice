package com.ss.smartoffice.shared.model.faq;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity

@Table(name = "t_faq")
public class FAQ {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String mFaqCatId;
	@Formula("(select faqCat.name from m_faq_category faqCat where faqCat.id=m_faq_cat_id)")
	private String faqCatName;
	@Formula("(select faqCat.description from m_faq_category faqCat where faqCat.id=m_faq_cat_id)")
	private String faqCatDesc;
	private String header;
	@Formula("(select count(*) from t_faq_like faqLike where faqLike.t_faq_id=id and faqLike.is_liked='Y')")
	private String totalLike;
	@Formula("(select count(*) from t_faq_like faqLike where faqLike.t_faq_id=id and faqLike.is_liked='N')")
	private String totalDisLike;
	@Column(name = "post", columnDefinition = "blob")
	private byte[] post;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_faq_id")
	private List<FAQLike> faqLikes;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_faq_id")
	private List<FAQComment> faqComments;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_faq_id")
	private List<FAQRefLink> faqRefLinks;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_faq_id")
	private List<FAQTag> faqTags;

	private String isEnabled;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;

	public FAQ() {
		super();
		// TODO Auto-generated constructor stub
	}

	// for summary
	public FAQ(Integer id, String mFaqCatId, String faqCatName, String faqCatDesc, String header) {
		super();
		this.id = id;
		this.mFaqCatId = mFaqCatId;
		this.faqCatName = faqCatName;
		this.faqCatDesc = faqCatDesc;
		this.header = header;
	}

	public FAQ(Integer id, String mFaqCatId, String faqCatName, String faqCatDesc, String header, String totalLike,
			String totalDisLike, byte[] post, List<FAQLike> faqLikes, List<FAQComment> faqComments,
			List<FAQRefLink> faqRefLinks, List<FAQTag> faqTags, String isEnabled, String createdBy,
			LocalDateTime createdDt, String modifiedBy, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.mFaqCatId = mFaqCatId;
		this.faqCatName = faqCatName;
		this.faqCatDesc = faqCatDesc;
		this.header = header;
		this.totalLike = totalLike;
		this.totalDisLike = totalDisLike;
		this.post = post;
		this.faqLikes = faqLikes;
		this.faqComments = faqComments;
		this.faqRefLinks = faqRefLinks;
		this.faqTags = faqTags;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getmFaqCatId() {
		return mFaqCatId;
	}

	public void setmFaqCatId(String mFaqCatId) {
		this.mFaqCatId = mFaqCatId;
	}

	public String getFaqCatName() {
		return faqCatName;
	}

	public void setFaqCatName(String faqCatName) {
		this.faqCatName = faqCatName;
	}

	public String getFaqCatDesc() {
		return faqCatDesc;
	}

	public void setFaqCatDesc(String faqCatDesc) {
		this.faqCatDesc = faqCatDesc;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getTotalLike() {
		return totalLike;
	}

	public void setTotalLike(String totalLike) {
		this.totalLike = totalLike;
	}

	public String getTotalDisLike() {
		return totalDisLike;
	}

	public void setTotalDisLike(String totalDisLike) {
		this.totalDisLike = totalDisLike;
	}

	public byte[] getPost() {
		return post;
	}

	public void setPost(byte[] post) {
		this.post = post;
	}

	public List<FAQLike> getFaqLikes() {
		return faqLikes;
	}

	public void setFaqLikes(List<FAQLike> faqLikes) {
		this.faqLikes = faqLikes;
	}

	public List<FAQComment> getFaqComments() {
		return faqComments;
	}

	public void setFaqComments(List<FAQComment> faqComments) {
		this.faqComments = faqComments;
	}

	public List<FAQRefLink> getFaqRefLinks() {
		return faqRefLinks;
	}

	public void setFaqRefLinks(List<FAQRefLink> faqRefLinks) {
		this.faqRefLinks = faqRefLinks;
	}

	public List<FAQTag> getFaqTags() {
		return faqTags;
	}

	public void setFaqTags(List<FAQTag> faqTags) {
		this.faqTags = faqTags;
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

	public LocalDateTime getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(LocalDateTime createdDt) {
		this.createdDt = createdDt;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedDt() {
		return modifiedDt;
	}

	public void setModifiedDt(LocalDateTime modifiedDt) {
		this.modifiedDt = modifiedDt;
	}

	@Override
	public String toString() {
		return "FAQ [id=" + id + ", mFaqCatId=" + mFaqCatId + ", faqCatName=" + faqCatName + ", faqCatDesc="
				+ faqCatDesc + ", header=" + header + ", totalLike=" + totalLike + ", totalDisLike=" + totalDisLike
				+ ", post=" + Arrays.toString(post) + ", faqLikes=" + faqLikes + ", faqComments=" + faqComments
				+ ", faqRefLinks=" + faqRefLinks + ", faqTags=" + faqTags + ", isEnabled=" + isEnabled + ", createdBy="
				+ createdBy + ", createdDt=" + createdDt + ", modifiedBy=" + modifiedBy + ", modifiedDt=" + modifiedDt
				+ "]";
	}

}