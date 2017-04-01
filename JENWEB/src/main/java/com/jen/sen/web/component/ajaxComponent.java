package com.jen.sen.web.component;

import java.util.List;
import java.util.Map;

import com.jen.sen.commons.Constant;
import com.jen.sen.persistence.core.dao.tools.PageDT;

/**
 * ajax封装类,用于controller以ajax返回页面
 * filename ajaxComponent.java
 * company jen
 * @author jenson
 * @email jenson2000@sina.com
 */
public class ajaxComponent<T> {
	/**
	 * datatable返回参数。
	 * 
	 */
	private Integer draw;

	/**
	 * 即没有过滤的记录数（数据库里总共记录数),datatable此项目不用,只用recordsFiltered
	 * 
	 */
	private Integer recordsTotal;

	/**
	 * 过滤后的记录数（如果有接收到前台的过滤条件，则返回的是过滤后的记录数）
	 * 
	 */
	private Integer recordsFiltered;

	/**
	 * VO结果集。
	 * 
	 */
	private List<T> data;
	
	private List<T> dataSec;
	
	private List<Map<String, Object>> map;
	
	private List<Map<String, Object>> mapSec;
	
	private T voObject ;

	/**
	 * page:页对象。
	 * 
	 */
	private PageDT page;
	
	private String msg=Constant.SYSTEM_SUCCESS_INFO;

	/**
	 * page Getter。
	 * 
	 * @return page
	 */
	public PageDT getPage() {
		return page;
	}

	private int messageFlag;
	/**
	 * page Setter。
	 * 
	 * @param page
	 *            设定参数
	 */
	public void setPage(PageDT page) {
		this.page = page;
	}	

	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	 
	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public T getVoObject() {
		return voObject;
	}

	public void setVoObject(T voObject) {
		this.voObject = voObject;
	}

	public int getMessageFlag() {
		return messageFlag;
	}

	public void setMessageFlag(int messageFlag) {
		this.messageFlag = messageFlag;
	}

	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<T> getDataSec() {
		return dataSec;
	}

	public void setDataSec(List<T> dataSec) {
		this.dataSec = dataSec;
	}

	public List<Map<String, Object>> getMap() {
		return map;
	}

	public void setMap(List<Map<String, Object>> map) {
		this.map = map;
	}

	public List<Map<String, Object>> getMapSec() {
		return mapSec;
	}

	public void setMapSec(List<Map<String, Object>> mapSec) {
		this.mapSec = mapSec;
	}
	
	

}
