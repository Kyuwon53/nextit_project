package com.mayday.news.service;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mayday.common.vo.PagingVO;

@SuppressWarnings("serial")
public class NewsSearchVO extends PagingVO{
private String searchCate;
private String searchCateType;
private String searchWord;
private String searchOrder;

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
public String getSearchOrder() {
	return searchOrder;
}
public void setSearchOrder(String searchOrder) {
	this.searchOrder = searchOrder;
}



}
