package com.ss.smartoffice.shared.model.employee;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;

import lombok.Data;


@Entity
@Table(name="m_emp_skill")

@Component
@Scope("prototype")
@Data
public class EmployeeSkill extends BaseEntity {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int id;
	private String skillLevelCode;
	private String createdBy;
	private String modifiedBy;
	private String isEnabled;
	@Column(name="m_employee_id")
	private int mEmployeeId;
	
	@Column(name="m_service_id")
	private int mProfileId;
	@Formula("(select service.service_name from m_service service left join m_material_service mat on service.id = mat.ability_id where mat.id=m_service_id)")
	private String serviceName;
	
	@Column(name="m_product_id")
	private int mProductId;
	@Formula("(SELECT product.material_name FROM m_material product WHERE product.id=m_product_id)")
	private String productName;
	
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public EmployeeSkill() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
