package com.mayday.reply.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mayday.common.vo.PagingVO;

@SuppressWarnings("serial")
public class ReplySearchVO extends PagingVO{
	private String reCategory ;
	private int reParentNo;
	private String reMemId;
	private String reLikeRank;
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getReLikeRank() {
		return reLikeRank;
	}


	public void setReLikeRank(String reLikeRank) {
		this.reLikeRank = reLikeRank;
	}





	public String getReCategory() {
		return reCategory;
	}
	public void setReCategory(String reCategory) {
		this.reCategory = reCategory;
	}
	public int getReParentNo() {
		return reParentNo;
	}
	public void setReParentNo(int reParentNo) {
		this.reParentNo = reParentNo;
	}

	public String getReMemId() {
		return reMemId;
	}

	public void setReMemId(String reMemId) {
		this.reMemId = reMemId;
	}
	
}
