package com.mayday.faq.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mayday.common.valid.Modify;

@SuppressWarnings("serial")
public class FaqVO implements Serializable{
	@NotNull(groups=Modify.class)
	private int boNo;             /*글번호*/
	@NotBlank(message = "분류 선택하세요.")
	private String faqCd;         /*구분코드*/
	private String memId;         /*아이디*/
	private String memName;		  /*이름*/
	@NotBlank(message = "제목을 입력하세요.")
	private String boTitle;       /*제목*/
	@NotBlank(message = "질문을 입력하세요.")
	private String boQue;         /*질문*/
	@NotBlank(message = "내용을 입력하세요.")
	private String boContent;     /*내용*/
	private int boHit;            /*조회수*/
	private String codeName;			/* 코드명 */
	private String codeParentCd;	/* 코드 부모명*/
	private int rowNo;				/* 리스트 순서*/
	
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
	public String getFaqCd() {
		return faqCd;
	}
	public void setFaqCd(String faqCd) {
		this.faqCd = faqCd;
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
	public String getBoQue() {
		return boQue;
	}
	public void setBoQue(String boQue) {
		this.boQue = boQue;
	}
	public String getBoContent() {
		return boContent;
	}
	public void setBoContent(String boContent) {
		this.boContent = boContent;
	}
	public int getBoHit() {
		return boHit;
	}
	public void setBoHit(int boHit) {
		this.boHit = boHit;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeParentCd() {
		return codeParentCd;
	}

	public void setCodeParentCd(String codeParentCd) {
		this.codeParentCd = codeParentCd;
	}

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}
	
}
