package com.mayday.law.service;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mayday.common.vo.PagingVO;

@SuppressWarnings("serial")
public class LawSearchVO extends PagingVO{
private String searchCate;
private String searchClass;
private String searchCateType;
private String searchWord;
private String searchDateOr;
private String searchStartOr;
private String searchDateType;
public String getSearchDateType() {
	return searchDateType;
}
public void setSearchDateType(String searchDateType) {
	this.searchDateType = searchDateType;
}
public String getSearchDateStart() {
	return searchDateStart;
}
public void setSearchDateStart(String searchDateStart) {
	this.searchDateStart = searchDateStart;
}
public String getSearchDateEnd() {
	return searchDateEnd;
}
public void setSearchDateEnd(String searchDateEnd) {
	this.searchDateEnd = searchDateEnd;
}
private String searchDateStart;
private String searchDateEnd;

@Override
	public String toString() {
	return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
}
public String getSearchCate() {
	return searchCate;
}
public void setSearchCate(String searchCate) {
	this.searchCate = searchCate;
}
public String getSearchClass() {
	return searchClass;
}
public void setSearchClass(String searchClass) {
	this.searchClass = searchClass;
}
public String getSearchCateType() {
	return searchCateType;
}
public void setSearchCateType(String searchCateType) {
	this.searchCateType = searchCateType;
}
public String getSearchWord() {
	return searchWord;
}
public void setSearchWord(String searchWord) {
	this.searchWord = searchWord;
}
public String getSearchDateOr() {
	return searchDateOr;
}
public void setSearchDateOr(String searchDateOr) {
	this.searchDateOr = searchDateOr;
}
public String getSearchStartOr() {
	return searchStartOr;
}
public void setSearchStartOr(String searchStartOr) {
	this.searchStartOr = searchStartOr;
}
}
