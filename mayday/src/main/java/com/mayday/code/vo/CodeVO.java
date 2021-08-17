package com.mayday.code.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@SuppressWarnings("serial")
public class CodeVO implements Serializable{
	private String codeCd;                  /*구분코드*/
	private String codeParentCd;            /*구분부모코드*/
	private String codeName;                /*구분이름*/
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
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
	
	
}
