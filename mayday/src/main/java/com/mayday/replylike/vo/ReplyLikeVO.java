package com.mayday.replylike.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ReplyLikeVO {
	private int likeNo;                  /* 번호*/
	private String memId;              /* 회원아이디*/
	private int replyNo;               /* 댓글번호*/
	private int memLikeNo;             /* 사용자 좋아요 댓글 번호*/
	private int boNo;  					/*글번호*/
	private int likeHit;					/* 좋아요 수 */
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
	public int getMemLikeNo() {
		return memLikeNo;
	}

	public void setMemLikeNo(int memLikeNo) {
		this.memLikeNo = memLikeNo;
	}


	
	public int getLikeNo() {
		return likeNo;
	}

	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
	}

	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public int getBoNo() {
		return boNo;
	}

	public void setBoNo(int boNo) {
		this.boNo = boNo;
	}

	public int getLikeHit() {
		return likeHit;
	}

	public void setLikeHit(int likeHit) {
		this.likeHit = likeHit;
	}
	
}
