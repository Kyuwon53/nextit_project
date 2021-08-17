package com.mayday.reply.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ReplyVO {
	private int reNo;                  /* 댓글번호*/
	private String reCategory;         /* 분류*/
	private int reParentNo;            /* 부모 번호*/
	private String reMemId;            /* 작성자ID*/
	private String reIp;               /* IP*/
	private String reRegDate;          /* 댓글 등록일자*/
	private String reModDate;          /* 댓글 수정일자*/
	private String reContent;          /* 댓글 내용*/
	private String reMemName;          /* 작성자 이름*/
	private String rowNo;          /* 댓글 순서*/
	private int likeHit;				/*댓글 좋아요 수*/
	
	public String getRowNo() {
		return rowNo;
	}
	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}
	public String getReMemName() {
		return reMemName;
	}
	public void setReMemName(String reMemName) {
		this.reMemName = reMemName;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	public int getReNo() {
		return reNo;
	}
	public void setReNo(int reNo) {
		this.reNo = reNo;
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
	public String getReIp() {
		return reIp;
	}
	public void setReIp(String reIp) {
		this.reIp = reIp;
	}
	public String getReRegDate() {
		return reRegDate;
	}
	public void setReRegDate(String reRegDate) {
		this.reRegDate = reRegDate;
	}
	public String getReModDate() {
		return reModDate;
	}
	public void setReModDate(String reModDate) {
		this.reModDate = reModDate;
	}
	public String getReContent() {
		return reContent;
	}
	public void setReContent(String reContent) {
		this.reContent = reContent;
	}
	public int getLikeHit() {
		return likeHit;
	}
	public void setLikeHit(int likeHit) {
		this.likeHit = likeHit;
	}
	
}
