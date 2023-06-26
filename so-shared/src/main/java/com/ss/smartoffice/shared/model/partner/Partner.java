package com.ss.smartoffice.shared.model.partner;

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
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "m_partner")

@Scope("prototype")
public class Partner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String clientCode;
	private String clientName;
	private String mobileNo;
	private String emailId;
	private String vendorCode;
	private String vendorName;
	private String vendorAbbreviation;
	private String gstNo;
	private String panNo;
	private String priFirstName;
	private String priLastName;
	private String number;
	private String countryId;
	@Formula("(select con.country_name from s_country con where con.id=country_id)")
	private String countryName;
	private String address;
	private String tinNo;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String companyId;
	private String companyName;
	private String isClient;
	private String isVendor;
	private String logoDocId;
	@Formula("(SELECT CONCAT(doc.doc_location,'/',doc.doc_name) from d_doc_info doc where doc.doc_id =logo_doc_id)")
	private String logoUrl;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "partner_id")
	private List<PartnerEmployee> partnerEmployees;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "partner_id")
	private List<PartnerDocument> partnerDocuments;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "partner_id")
	private List<PartnerContact> partnerContacts;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "partner_id")
	private List<PartnerAccountInfo> partnerAccountInfos;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "partner_id")
	private List<PartnerGst> partnerGsts;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	private String referenceNumber;
	
	private String transactionCurrId;
	private String companyTypeId;
	@Formula("(SELECT sct.type_name from s_company_type sct where sct.id=company_type_id)")
	private String companyTypeName;
	@Formula("(SELECT sct.has_gst from s_company_type sct where sct.id=company_type_id)")
	private String companyHasGst;
	@Formula("(SELECT sct.has_overseas from s_company_type sct where sct.id=company_type_id)")
	private String companyHasOverseas;
	
	public Partner() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Partner(Integer id, String clientCode, String clientName, String isClient) {
		super();
		this.id = id;
		this.clientCode = clientCode;
		this.clientName = clientName;
		this.isClient = isClient;
	}

	public Partner(Integer id, String clientCode, String clientName, String mobileNo, String emailId, String vendorCode,
			String vendorName, String vendorAbbreviation, String gstNo, String panNo, String priFirstName,
			String priLastName, String number, String countryId, String countryName, String address, String tinNo,
			String addressLine1, String addressLine2, String city, String state, String companyId, String companyName,
			String isClient, String isVendor, String logoUrl, String isEnabled, String createdBy, String modifiedBy,
			List<PartnerEmployee> partnerEmployees, List<PartnerDocument> partnerDocuments,
			List<PartnerContact> partnerContacts, List<PartnerAccountInfo> partnerAccountInfos,
			List<PartnerGst> partnerGsts, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.clientCode = clientCode;
		this.clientName = clientName;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.vendorCode = vendorCode;
		this.vendorName = vendorName;
		this.vendorAbbreviation = vendorAbbreviation;
		this.gstNo = gstNo;
		this.panNo = panNo;
		this.priFirstName = priFirstName;
		this.priLastName = priLastName;
		this.number = number;
		this.countryId = countryId;
		this.countryName = countryName;
		this.address = address;
		this.tinNo = tinNo;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.companyId = companyId;
		this.companyName = companyName;
		this.isClient = isClient;
		this.isVendor = isVendor;
		this.logoUrl = logoUrl;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.partnerEmployees = partnerEmployees;
		this.partnerDocuments = partnerDocuments;
		this.partnerContacts = partnerContacts;
		this.partnerAccountInfos = partnerAccountInfos;
		this.partnerGsts = partnerGsts;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}

	

	public Partner(Integer id, String clientCode, String clientName, String mobileNo, String emailId, String vendorCode,
			String vendorName, String vendorAbbreviation, String gstNo, String panNo, String priFirstName,
			String priLastName, String number, String countryId, String countryName, String address, String tinNo,
			String addressLine1, String addressLine2, String city, String state, String companyId, String companyName,
			String isClient, String isVendor, String logoDocId, String logoUrl, String isEnabled, String createdBy,
			String modifiedBy, List<PartnerEmployee> partnerEmployees, List<PartnerDocument> partnerDocuments,
			List<PartnerContact> partnerContacts, List<PartnerAccountInfo> partnerAccountInfos,
			List<PartnerGst> partnerGsts, LocalDateTime createdDt, LocalDateTime modifiedDt, String referenceNumber,
			String transactionCurrId, String companyTypeId, String companyTypeName, String companyHasGst,
			String companyHasOverseas) {
		super();
		this.id = id;
		this.clientCode = clientCode;
		this.clientName = clientName;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.vendorCode = vendorCode;
		this.vendorName = vendorName;
		this.vendorAbbreviation = vendorAbbreviation;
		this.gstNo = gstNo;
		this.panNo = panNo;
		this.priFirstName = priFirstName;
		this.priLastName = priLastName;
		this.number = number;
		this.countryId = countryId;
		this.countryName = countryName;
		this.address = address;
		this.tinNo = tinNo;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.companyId = companyId;
		this.companyName = companyName;
		this.isClient = isClient;
		this.isVendor = isVendor;
		this.logoDocId = logoDocId;
		this.logoUrl = logoUrl;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.partnerEmployees = partnerEmployees;
		this.partnerDocuments = partnerDocuments;
		this.partnerContacts = partnerContacts;
		this.partnerAccountInfos = partnerAccountInfos;
		this.partnerGsts = partnerGsts;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.referenceNumber = referenceNumber;
		this.transactionCurrId = transactionCurrId;
		this.companyTypeId = companyTypeId;
		this.companyTypeName = companyTypeName;
		this.companyHasGst = companyHasGst;
		this.companyHasOverseas = companyHasOverseas;
	}

	@Override
	public String toString() {
		return "Partner [id=" + id + ", clientCode=" + clientCode + ", clientName=" + clientName + ", mobileNo="
				+ mobileNo + ", emailId=" + emailId + ", vendorCode=" + vendorCode + ", vendorName=" + vendorName
				+ ", vendorAbbreviation=" + vendorAbbreviation + ", gstNo=" + gstNo + ", panNo=" + panNo
				+ ", priFirstName=" + priFirstName + ", priLastName=" + priLastName + ", number=" + number
				+ ", countryId=" + countryId + ", countryName=" + countryName + ", address=" + address + ", tinNo="
				+ tinNo + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", city=" + city
				+ ", state=" + state + ", companyId=" + companyId + ", companyName=" + companyName + ", isClient="
				+ isClient + ", isVendor=" + isVendor + ", logoDocId=" + logoDocId + ", logoUrl=" + logoUrl
				+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", partnerEmployees=" + partnerEmployees + ", partnerDocuments=" + partnerDocuments
				+ ", partnerContacts=" + partnerContacts + ", partnerAccountInfos=" + partnerAccountInfos
				+ ", partnerGsts=" + partnerGsts + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt
				+ ", referenceNumber=" + referenceNumber + ", transactionCurrId=" + transactionCurrId
				+ ", companyTypeId=" + companyTypeId + ", companyTypeName=" + companyTypeName + ", companyHasGst="
				+ companyHasGst + ", companyHasOverseas=" + companyHasOverseas + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorAbbreviation() {
		return vendorAbbreviation;
	}

	public void setVendorAbbreviation(String vendorAbbreviation) {
		this.vendorAbbreviation = vendorAbbreviation;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getPriFirstName() {
		return priFirstName;
	}

	public void setPriFirstName(String priFirstName) {
		this.priFirstName = priFirstName;
	}

	public String getPriLastName() {
		return priLastName;
	}

	public void setPriLastName(String priLastName) {
		this.priLastName = priLastName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTinNo() {
		return tinNo;
	}

	public void setTinNo(String tinNo) {
		this.tinNo = tinNo;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTransactionCurrId() {
		return transactionCurrId;
	}

	public void setTransactionCurrId(String transactionCurrId) {
		this.transactionCurrId = transactionCurrId;
	}

	public String getIsClient() {
		return isClient;
	}

	public void setIsClient(String isClient) {
		this.isClient = isClient;
	}

	public String getIsVendor() {
		return isVendor;
	}

	public void setIsVendor(String isVendor) {
		this.isVendor = isVendor;
	}
	
	public String getLogoDocId() {
		return logoDocId;
	}

	public void setLogoDocId(String logoDocId) {
		this.logoDocId = logoDocId;
	}

	public String getCompanyHasOverseas() {
		return companyHasOverseas;
	}

	public void setCompanyHasOverseas(String companyHasOverseas) {
		this.companyHasOverseas = companyHasOverseas;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getCompanyTypeId() {
		return companyTypeId;
	}

	public void setCompanyTypeId(String companyTypeId) {
		this.companyTypeId = companyTypeId;
	}

	public String getCompanyTypeName() {
		return companyTypeName;
	}

	public void setCompanyTypeName(String companyTypeName) {
		this.companyTypeName = companyTypeName;
	}

	public String getCompanyHasGst() {
		return companyHasGst;
	}

	public void setCompanyHasGst(String companyHasGst) {
		this.companyHasGst = companyHasGst;
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

	public List<PartnerEmployee> getPartnerEmployees() {
		return partnerEmployees;
	}

	public void setPartnerEmployees(List<PartnerEmployee> partnerEmployees) {
		this.partnerEmployees = partnerEmployees;
	}

	public List<PartnerDocument> getPartnerDocuments() {
		return partnerDocuments;
	}

	public void setPartnerDocuments(List<PartnerDocument> partnerDocuments) {
		this.partnerDocuments = partnerDocuments;
	}

	public List<PartnerContact> getPartnerContacts() {
		return partnerContacts;
	}

	public void setPartnerContacts(List<PartnerContact> partnerContacts) {
		this.partnerContacts = partnerContacts;
	}

	public List<PartnerAccountInfo> getPartnerAccountInfos() {
		return partnerAccountInfos;
	}

	public void setPartnerAccountInfos(List<PartnerAccountInfo> partnerAccountInfos) {
		this.partnerAccountInfos = partnerAccountInfos;
	}

	public List<PartnerGst> getPartnerGsts() {
		return partnerGsts;
	}

	public void setPartnerGsts(List<PartnerGst> partnerGsts) {
		this.partnerGsts = partnerGsts;
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

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	
	

}