package com.mayday.board.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@SuppressWarnings("serial")
public class BoardVO implements Serializable{
	
	private int boNo;                   /* 글번호 */
	private String boCd;               /* 구분코드 */
	private String memId;              /* 아이디 */
	private String boTitle;            /* 제목 */
	private String boContent;          /* 내용 */
	private String boRegDate;          /* 입력시간 */
	private String boModDate;          /* 수정시간 */
	private int boHit;                  /* 조회수 */
	private int rowNo;				/* 리스트 순서*/
	
	private String codeCd;             /* 구분코드 */
	private String codeParentCd;       /* 구분부모코드 */
	private String codeName;           /* 구분이름 */
	private String memName;		  /*이름*/
	
	// 답글 
	private int originNo; 		/*원글 번호*/
	private int groupOrd;		/*원글에 대한 순서 */
	private int groupLayer;		/*답글 계층*/
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
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

	public String getBoModDate() {
		return boModDate;
	}

	public void setBoModDate(String boModDate) {
		this.boModDate = boModDate;
	}

	public int getBoHit() {
		return boHit;
	}

	public void setBoHit(int boHit) {
		this.boHit = boHit;
	}

	public String getCodeCd() {
		return codeCd;
	}

	public void setCodeCd(String codeCd) {
		this.codeCd = codeCd;
	}

	public String getCodeParentCd() {
		return codeParentCd;
	}

	public void setCodeParentCd(String codeParentCd) {
		this.codeParentCd = codeParentCd;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

	public int getOriginNo() {
		return originNo;
	}

	public void setOriginNo(int originNo) {
		this.originNo = originNo;
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
	
}
