package com.mayday.qna.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mayday.common.vo.PagingVO;

@SuppressWarnings("serial")
public class QnASearchVO extends PagingVO {
	private String searchType;		// 검색 분류 ( 제목, 아이디 , 내용 ) 
	private String searchWord;		// 검색어 
	private String searchCheck;		// 답변 확인 분류 
	
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
	public String getSearchCheck() {
		return searchCheck;
	}
	public void setSearchCheck(String searchCheck) {
		this.searchCheck = searchCheck;
	}
	
}
