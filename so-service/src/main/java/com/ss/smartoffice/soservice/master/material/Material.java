package com.ss.smartoffice.soservice.master.material;

import java.time.LocalDateTime;
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
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name="m_material")

@Data
public class Material {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String materialCategory;
	private String materialCode;
	private String materialName;
	private String materialDesc;
	private String remarks;
	private String hasInventory;
	private String statusCode;
	private String unitOfMeasure;
	private String hsnCode;
	private String unitPrice;
	@Column(name="m_manufacturer_id")
	private String manufacturerId;
	@Column(name="m_product_family_id")
	private String productFamilyId;
	private String assetMake;
	private String assetModel;
	private String assetOwner;
	private String feederEst;
	private String motorEst;
	private String generatorEst;
	private String lineEst;
	private String lineDiffEst;
	private String stockValue;
	private String trafoEst;
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
	@JoinColumn(name="m_material_id")
	private List<MaterialService>materialServices;
	

}
