package com.ss.smartoffice.soservice.transaction.appraisalservice;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name="t_appraisal_skill_goal")

@Data
public class AppraisalSkillGoal {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="t_appraisal_hdr_id")
	private String appraisalHdrId;
	@Column(name="m_product_id")
	private String productId;
	@Column(name="m_ability_id")
	private String abilityId;
	@Formula("(SELECT product.material_name FROM m_material product WHERE product.id=m_product_id)")
	private String productName;
	@Formula("(select service.service_name from m_service service left join m_material_service mat on service.id = mat.ability_id where mat.id=m_ability_id)")
	private String abilityName;
	private String isExistingSkill;
	private Integer currSkillLevel;
	private Integer expectedSkillLevel;
	@Column(name="skill_level_emp")
	private Integer skillLevelFromEmp;
	@Column(name="skill_level_n1")
	private Integer skillLevelFromN1;
	@Column(name="skill_level_final")
	private Integer skillLevelFinal;
	private String isMandatory;
	private String empRemarks;
	@Column(name="n1_remarks")
	private String n1Remarks;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
}
