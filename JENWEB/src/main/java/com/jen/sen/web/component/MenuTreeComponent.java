package com.jen.sen.web.component;

import java.io.Serializable;
import java.util.List;
/**
 * 菜单vo
 * filename MenuTreeComponent.java
 * company jen
 * @author jenson
 * @email jenson2000@sina.com
 */
public class MenuTreeComponent implements Serializable {
	
	private static final long serialVersionUID = -1523002859550851821L;
	/** 单菜ID */
	private Long rightId;
	/** 父菜单ID */
	private Long rightParentId;
	/** 子菜单列表 */
	private List<MenuTreeComponent> subMenus;
	/** 菜单级别 */
	private Integer rightLevel;
	/** 菜单排序值 */
	private Integer rightOrder;
	/** 菜单编号 */
	private String rightCode;
	/** 菜单名称 */
	private String rightName;	
	/** 菜单图标  font awesome*/
	private String rightImg;
	/** 菜单访问URL */
	private String rightUrl;
	/** 菜单访问授权 */
	private String perms;
	public Long getRightId() {
		return rightId;
	}
	public void setRightId(Long rightId) {
		this.rightId = rightId;
	}
	public Long getRightParentId() {
		return rightParentId;
	}
	public void setRightParentId(Long rightParentId) {
		this.rightParentId = rightParentId;
	}
	public List<MenuTreeComponent> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<MenuTreeComponent> subMenus) {
		this.subMenus = subMenus;
	}
	public Integer getRightLevel() {
		return rightLevel;
	}
	public void setRightLevel(Integer rightLevel) {
		this.rightLevel = rightLevel;
	}
	public Integer getRightOrder() {
		return rightOrder;
	}
	public void setRightOrder(Integer rightOrder) {
		this.rightOrder = rightOrder;
	}
	public String getRightCode() {
		return rightCode;
	}
	public void setRightCode(String rightCode) {
		this.rightCode = rightCode;
	}
	public String getRightName() {
		return rightName;
	}
	public void setRightName(String rightName) {
		this.rightName = rightName;
	}
	public String getRightUrl() {
		return rightUrl;
	}
	public void setRightUrl(String rightUrl) {
		this.rightUrl = rightUrl;
	}
	public String getRightImg() {
		return rightImg;
	}
	public void setRightImg(String rightImg) {
		this.rightImg = rightImg;
	}
	public String getPerms() {
		return perms;
	}
	public void setPerms(String perms) {
		this.perms = perms;
	}
	

}
