package com.jen.sen.persistence.core.dao.tools;

import com.jen.sen.commons.Constant;
/**
 * 翻页工具类
 * filename PageDT.java
 * company jen
 * @author jenson
 * @email jenson2000@sina.com
 */
public class PageDT {
	
	/***
	 * 构造函数初始化lengt长度为10,默认每页显示10条
	 * @param draw
	 * @param start
	 * auth by jenson.wang
	 */
	public PageDT(int draw, int start) {
		this.draw=draw;
		this.start=start;
		this.length=Constant.page_lenght;
	}
	

	/**
	 * 当前页号, 采用自然数计数 1,2,3,...
	 */
	private int draw;
	
	
	/**
	 * 第一条数据的起始位置，比如0代表第一条数据
	 */
	private int start;
	
	/**
	 * 页面大小(每页显示的条数),-1，代表需要返回全部数据
	 */	
	private int length;
	
	/**
	 * 即没有过滤的记录数（数据库里总共记录数),datatable此项目不用,只用recordsFiltered
	 */	
	private int recordsTotal;
	
	/**
	 * 过滤后的记录数（如果有接收到前台的过滤条件，则返回的是过滤后的记录数）
	 */
	private int recordsFiltered;

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
		

}
