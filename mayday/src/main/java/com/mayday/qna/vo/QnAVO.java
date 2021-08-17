package com.mayday.qna.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class QnAVO {
	private int boNo;                       /*글번호*/
	private String memId;                   /* 작성자 아이디*/
	private String boTitle;                 /*제목*/
	private String boContent;               /*내용*/
	private String boRegDate;               /*작성일자*/
	private int originalNo;                 /*원글번호*/
	private int groupOrd;                   /*답글 순서*/
	private int groupLayer;                 /*답글 계층*/
	private String boCheck;                 /*문의 답글 여부 원글 -> 답변시 변화 , 답글 -> 확인 시 변화*/
	private String ansMemId;                /*원글 작성자 아이디*/
	private String memName;					 /* 작성자 이름 */
	private String expertName;				 /* 변호사/노무사 이름 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	public int getBoNo() {
		return boNo;
	}
	public void setBoNo(int boNo) {
		this.boNo = boNo;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getBoTitle() {
		return boTitle;
	}
	public void setBoTitle(String boTitle) {
		this.boTitle = boTitle;
	}
	public String getBoContent() {
		return boContent;
	}
	public void setBoContent(String boContent) {
		this.boContent = boContent;
	}
	public String getBoRegDate() {
		return boRegDate;
	}
	public void setBoRegDate(String boRegDate) {
		this.boRegDate = boRegDate;
	}
	public int getOriginalNo() {
		return originalNo;
	}
	public void setOriginalNo(int originalNo) {
		this.originalNo = originalNo;
	}
	public int getGroupOrd() {
		return groupOrd;
	}
	public void setGroupOrd(int groupOrd) {
		this.groupOrd = groupOrd;
	}
	public int getGroupLayer() {
		return groupLayer;
	}
	public void setGroupLayer(int groupLayer) {
		this.groupLayer = groupLayer;
	}

	public String getBoCheck() {
		return boCheck;
	}

	public void setBoCheck(String boCheck) {
		this.boCheck = boCheck;
	}

	public String getAnsMemId() {
		return ansMemId;
	}

	public void setAnsMemId(String ansMemId) {
		this.ansMemId = ansMemId;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getExpertName() {
		return expertName;
	}

	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}
	
}
