package com.mayday.law.service;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class LawVO {
	private int boNo;                  /* 번호*/
	private String lawName;            /* 법령명*/
	private String lawDate;            /* 공포일자*/
	private String lawCate;            /* 법령종류*/
	private String lawNo;              /* 공포번호*/
	private String lawStartDate;       /* 시행일자*/
	private String lawClass;           /* 재정.개정구분*/
	private String lawDep;             /* 소관부처*/
	private String lawLink;            /* 내용링크*/
	private int rowNo;						/* 순서 */
	
	public int getRowNo() {
		return rowNo;
	}
	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	public int getBoNo() {
		return boNo;
	}
	public void setBoNo(int boNo) {
		this.boNo = boNo;
	}
	public String getLawName() {
		return lawName;
	}
	public void setLawName(String lawName) {
		this.lawName = lawName;
	}
	public String getLawDate() {
		return lawDate;
	}
	public void setLawDate(String lawDate) {
		this.lawDate = lawDate;
	}
	public String getLawCate() {
		return lawCate;
	}
	public void setLawCate(String lawCate) {
		this.lawCate = lawCate;
	}
	public String getLawNo() {
		return lawNo;
	}
	public void setLawNo(String lawNo) {
		this.lawNo = lawNo;
	}
	public String getLawStartDate() {
		return lawStartDate;
	}
	public void setLawStartDate(String lawStartDate) {
		this.lawStartDate = lawStartDate;
	}
	public String getLawClass() {
		return lawClass;
	}
	public void setLawClass(String lawClass) {
		this.lawClass = lawClass;
	}
	public String getLawDep() {
		return lawDep;
	}
	public void setLawDep(String lawDep) {
		this.lawDep = lawDep;
	}
	public String getLawLink() {
		return lawLink;
	}
	public void setLawLink(String lawLink) {
		this.lawLink = lawLink;
	}
	
	
}
