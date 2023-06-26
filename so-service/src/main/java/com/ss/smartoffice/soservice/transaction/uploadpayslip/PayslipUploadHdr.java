package com.ss.smartoffice.soservice.transaction.uploadpayslip;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.ss.smartoffice.soservice.transaction.model.UploadPayslipLine;

import lombok.Data;

@Data
@JsonPropertyOrder({"ID","UPLOAD_CODE","UPLOAD_DATETIME","PAY_MONTH","PAY_YEAR","PROCESS_STATUS","RECORDS_UPLOADED","SUCCESS_COUNT",
	"DOC_ID","CREATED_BY","MODIFIED_BY","COMP_PAYCYCLE_LINE_ID","CREATED_DT","MODIFIED_DT","UPLOADPAYSLIPLINES"})
public class PayslipUploadHdr {
	@JsonProperty("ID")
	public Integer id;
	@JsonProperty("UPLOAD_CODE")
	public String uploadCode;
	@JsonProperty("UPLOAD_DATETIME")
	private LocalDateTime uploadDatetime;
	@JsonProperty("PAY_MONTH")
	private Integer payMonth;
	@JsonProperty("PAY_YEAR")
	private Integer payYear ;
	@JsonProperty("PROCESS_STATUS")
	private String  processStatus ;
	@JsonProperty("RECORDS_UPLOADED")
	private String  recordsUploaded ;
	@JsonProperty("SUCCESS_COUNT")
	private Integer successCount ;
	@JsonProperty("DOC_ID")
	private String docId ;
	@JsonProperty("CREATED_BY")
	private String createdBy;
	@JsonProperty("MODIFIED_BY")
	private String modifiedBy;
	@JsonProperty("COMP_PAYCYCLE_LINE_ID")
	private Integer compPayCycleLineId;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonProperty("CREATED_DT")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonProperty("MODIFIED_DT")
	private LocalDateTime modifiedDt;
	
	
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="upload_payslip_hdr_id")
	@JsonProperty("UPLOADPAYSLIPLINES")
	private List<UploadPayslipLine> uploadPayslipLines;
	public String isClean;
	public String remarks;
}
