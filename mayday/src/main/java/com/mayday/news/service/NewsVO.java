package com.mayday.news.service;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class NewsVO {
	private int boNo;                  /* 글번호*/
	private String boCd;               /* 구분코드*/
	private String newsTitle;          /* 기사 제목*/
	private String newsPress;          /* 언론사*/
	private String newsRegDate;        /* 기사입력날짜*/
	private String newsLink;           /* 링크*/
	private String boCdName;           /* 구분코드이름*/
	private int rowNo;
	
	public int getRowNo() {
		return rowNo;
	}
	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}
	public String getBoCdName() {
		return boCdName;
	}
	public void setBoCdName(String boCdName) {
		this.boCdName = boCdName;
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
	public String getBoCd() {
		return boCd;
	}
	public void setBoCd(String boCd) {
		this.boCd = boCd;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNewsPress() {
		return newsPress;
	}
	public void setNewsPress(String newsPress) {
		this.newsPress = newsPress;
	}
	public String getNewsRegDate() {
		return newsRegDate;
	}
	public void setNewsRegDate(String newsRegDate) {
		this.newsRegDate = newsRegDate;
	}
	public String getNewsLink() {
		return newsLink;
	}
	public void setNewsLink(String newsLink) {
		this.newsLink = newsLink;
	}
}
