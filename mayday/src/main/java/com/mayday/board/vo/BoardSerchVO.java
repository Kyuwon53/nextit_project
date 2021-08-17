package com.mayday.board.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mayday.common.vo.PagingVO;

@SuppressWarnings("serial")
public class BoardSerchVO extends PagingVO{

	private String searchCate; 	// 분류 
	private String searchType;	// 검색 분류 ( 제목, 구분코드 , 내용 ) 
	private String searchWord;	// 검색어 
	private String searchOrder;	// 조회순
	private String boardParentCd;	  /*구분 부모 코드*/
	private String memId;			/* 마이페이지 내가 쓴 글 불러올 때 파라미터 */
	private String searchCd;   // 구분코드 
	
	public String getSearchCd() {
		return searchCd;
	}

	public void setSearchCd(String searchCd) {
		this.searchCd = searchCd;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getSearchCate() {
		return searchCate;
	}

	public void setSearchCate(String searchCate) {
		this.searchCate = searchCate;
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

	public String getBoardParentCd() {
		return boardParentCd;
	}

	public void setBoardParentCd(String boardParentCd) {
		this.boardParentCd = boardParentCd;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

}
