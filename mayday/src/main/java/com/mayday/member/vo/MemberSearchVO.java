package com.mayday.member.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mayday.common.vo.PagingVO;

@SuppressWarnings("serial")
public class MemberSearchVO extends PagingVO {
private String searchCateMb;
private String searchCateSt;
private String searchCateType;
private String searchWord;
private String memsearchCateSt;
public String getMemsearchCateSt() {
	return memsearchCateSt;
}
public void setMemsearchCateSt(String memsearchCateSt) {
	this.memsearchCateSt = memsearchCateSt;
}
public String getSearchWord() {
	return searchWord;
}
public void setSearchWord(String searchWord) {
	this.searchWord = searchWord;
}
@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
public String getSearchCateMb() {
	return searchCateMb;
}
public void setSearchCateMb(String searchCateMb) {
	this.searchCateMb = searchCateMb;
}
public String getSearchCateSt() {
	return searchCateSt;
}
public void setSearchCateSt(String searchCateSt) {
	this.searchCateSt = searchCateSt;
}
public String getSearchCateType() {
	return searchCateType;
}
public void setSearchCateType(String searchCateType) {
	this.searchCateType = searchCateType;
}

}
