package com.jen.sen.web.vo.system;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.BeanUtils;

import com.jen.sen.persistence.pojo.system.TRoles;


public class RoleVo implements Serializable {

	private static final long serialVersionUID = 7937360943551211675L;
	private Long roleId;
	private String roleCode;
	private String roleName;
	private Integer roleOrder;
	private Short status;
	private String remark;
	private Long createUserid;
	private Date createTime;
	private Long updateUserid;
	private Date updateTime;
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getRoleOrder() {
		return roleOrder;
	}
	public void setRoleOrder(Integer roleOrder) {
		this.roleOrder = roleOrder;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getCreateUserid() {
		return createUserid;
	}
	public void setCreateUserid(Long createUserid) {
		this.createUserid = createUserid;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getUpdateUserid() {
		return updateUserid;
	}
	public void setUpdateUserid(Long updateUserid) {
		this.updateUserid = updateUserid;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public TRoles toPojo() {
		TRoles ob = new TRoles();
		BeanUtils.copyProperties(this, ob);
		return ob;
	}

	public void toVo(TRoles ob) {
		BeanUtils.copyProperties(ob, this);
	}

	

}
