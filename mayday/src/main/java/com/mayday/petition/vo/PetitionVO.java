package com.mayday.petition.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@SuppressWarnings("serial")
public class PetitionVO implements Serializable {
	private int boNo;                       /*글번호*/
	private String boCd;                    /*구분코드*/
	private String petTitle;                /*청원제목*/
	private String petContent;              /*청원내용*/
	private String petLink;                 /*청원링크*/
	private String petStartDate;            /*청원시작날짜*/
	private String petEndDate;              /*청원마감날짜*/
	private String petProgress;             /*청원상태*/
	private int rowNo;
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
	public String getBoCd() {
		return boCd;
	}
	public void setBoCd(String boCd) {
		this.boCd = boCd;
	}
	public String getPetTitle() {
		return petTitle;
	}
	public void setPetTitle(String petTitle) {
		this.petTitle = petTitle;
	}
	public String getPetContent() {
		return petContent;
	}
	public void setPetContent(String petContent) {
		this.petContent = petContent;
	}
	public String getPetLink() {
		return petLink;
	}
	public void setPetLink(String petLink) {
		this.petLink = petLink;
	}
	public String getPetStartDate() {
		return petStartDate;
	}
	public void setPetStartDate(String petStartDate) {
		this.petStartDate = petStartDate;
	}
	public String getPetEndDate() {
		return petEndDate;
	}
	public void setPetEndDate(String petEndDate) {
		this.petEndDate = petEndDate;
	}
	public String getPetProgress() {
		return petProgress;
	}
	public void setPetProgress(String petProgress) {
		this.petProgress = petProgress;
	}

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}
	
}
