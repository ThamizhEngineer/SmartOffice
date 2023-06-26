package com.ss.smartoffice.shared.model.faq;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Transient;

import lombok.Data;

@Entity

@Table(name = "m_faq_category")
@Data
public class FAQCategory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String code;
	private String name;
	private String description;
	@Formula("(SELECT COUNT(faq.id) from t_faq faq where faq.m_faq_cat_id=id)")
	private String tfaqCount;
	@Transient
	private List<FAQ> faqs;	
	private String isEnabled;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
}