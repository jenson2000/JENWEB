package com.jen.sen.persistence.pojo.system;
// Generated 2016-5-25 16:02:32 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TUser generated by hbm2java
 */
@Entity
@Table(name = "t_user", catalog = "sendb")
public class TUser implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long userId;
	private String userAccount;
	private String userPassword;
	private String userName;
	private Short userSex;
	private String userIphone;
	private Date userBirthday;
	private String userEmail;
	private String userAddress;
	private Short status;
	private String remark;
	private Long createUserid;
	private Date createTime;
	private Long updateUserid;
	private Date updateTime;
	private Set<TUserRoles> TUserRoleses = new HashSet<TUserRoles>(0);

	public TUser() {
	}

	public TUser(String userAccount, String userPassword, Short status) {
		this.userAccount = userAccount;
		this.userPassword = userPassword;
		this.status = status;
	}

	public TUser(String userAccount, String userPassword, String userName, Short userSex, String userIphone,
			Date userBirthday, String userEmail, String userAddress, Short status, String remark, Long createUserid,
			Date createTime, Long updateUserid, Date updateTime, Set<TUserRoles> TUserRoleses) {
		this.userAccount = userAccount;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userSex = userSex;
		this.userIphone = userIphone;
		this.userBirthday = userBirthday;
		this.userEmail = userEmail;
		this.userAddress = userAddress;
		this.status = status;
		this.remark = remark;
		this.createUserid = createUserid;
		this.createTime = createTime;
		this.updateUserid = updateUserid;
		this.updateTime = updateTime;
		this.TUserRoleses = TUserRoleses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "user_id", unique = true, nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "user_account", nullable = false, length = 50)
	public String getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	@Column(name = "user_password", nullable = false, length = 150)
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "user_name", length = 20)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "user_sex")
	public Short getUserSex() {
		return this.userSex;
	}

	public void setUserSex(Short userSex) {
		this.userSex = userSex;
	}

	@Column(name = "user_iphone", length = 30)
	public String getUserIphone() {
		return this.userIphone;
	}

	public void setUserIphone(String userIphone) {
		this.userIphone = userIphone;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "user_birthday", length = 19)
	public Date getUserBirthday() {
		return this.userBirthday;
	}

	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}

	@Column(name = "user_email", length = 100)
	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Column(name = "user_address", length = 200)
	public String getUserAddress() {
		return this.userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	@Column(name = "status", nullable = false)
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "remark", length = 300)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "create_userid")
	public Long getCreateUserid() {
		return this.createUserid;
	}

	public void setCreateUserid(Long createUserid) {
		this.createUserid = createUserid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_userid")
	public Long getUpdateUserid() {
		return this.updateUserid;
	}

	public void setUpdateUserid(Long updateUserid) {
		this.updateUserid = updateUserid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TUser")
	public Set<TUserRoles> getTUserRoleses() {
		return this.TUserRoleses;
	}

	public void setTUserRoleses(Set<TUserRoles> TUserRoleses) {
		this.TUserRoleses = TUserRoleses;
	}

}
