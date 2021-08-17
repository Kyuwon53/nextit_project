package com.mayday.petition.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mayday.common.vo.PagingVO;

@SuppressWarnings("serial")
public class PetitionSearchVO extends PagingVO {
	private String searchType;		// 검색 분류 ( 제목, 내용)
	private String searchWord;		// 검색어
	private String searchOrder;		// 조회순
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
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
