package com.ss.smartoffice.soauthservice.model.auth;
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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;



@Entity
@Table(name ="auth_menu")
public class AuthMenu extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String name;
	private String state;
	private String type;
	private String icon;
	private String userId;
	private String featureId;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	private Integer menuOrder;
//	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
//	@Fetch(value = FetchMode.SUBSELECT)
//	@JoinColumn(name="auth_menu_id")
//	private List<AuthSubMenu> authSubMenus;
	@Transient
	private List<AuthSubMenu> children;
	public AuthMenu() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AuthMenu(String id, String name, String state, String type, String icon, String userId, String featureId,
			String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt,
			Integer menuOrder, List<AuthSubMenu> children) {
		super();
		this.id = id;
		this.name = name;
		this.state = state;
		this.type = type;
		this.icon = icon;
		this.userId = userId;
		this.featureId = featureId;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.menuOrder = menuOrder;
		this.children = children;
	}
	
	public AuthMenu(String name, String state, String type, String icon, String userId, String featureId,
			List<AuthSubMenu> children) {
		super();
		this.name = name;
		this.state = state;
		this.type = type;
		this.icon = icon;
		this.userId = userId;
		this.featureId = featureId;
		this.children = children;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFeatureId() {
		return featureId;
	}
	public void setFeatureId(String featureId) {
		this.featureId = featureId;
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
	
	public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	public List<AuthSubMenu> getChildren() {
		return children;
	}

	public void setChildren(List<AuthSubMenu> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "AuthMenu [id=" + id + ", name=" + name + ", state=" + state + ", type=" + type + ", icon=" + icon
				+ ", userId=" + userId + ", featureId=" + featureId + ", isEnabled=" + isEnabled + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt
				+ ", menuOrder=" + menuOrder + ", children=" + children + "]";
	}
	
	
	
	
}
