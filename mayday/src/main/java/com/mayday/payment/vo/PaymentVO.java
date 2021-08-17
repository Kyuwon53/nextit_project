package com.mayday.payment.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import org.apache.commons.lang3.builder.ToStringStyle;
@SuppressWarnings("serial")
public class PaymentVO implements Serializable{
	private String memId;    	/* 아이디 */
	private String lawyerId;		/* 변호사 아이디 */
	private int itemNum;     	/* 주문번호 */
	private String itemName; 	/* 상품명 */
	private int quantity;    	/* 수량 */
	private int totalAmount; 	/* 총 가격 */
	private String taxFree;  	/* 비과세 */
	private String payRegDate;              /*결제일*/
	private String resDate;                 /*예약일*/
	
	private String lawyerName; 	/*변호사 이름*/
	private String clientName;	/*의뢰인 이름*/
	private String searchPosit;  /* 결제내역 조회한 사람이 의뢰인=C, 변호사=E*/
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getLawyerId() {
		return lawyerId;
	}
	public void setLawyerId(String lawyerId) {
		this.lawyerId = lawyerId;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getTaxFree() {
		return taxFree;
	}
	public void setTaxFree(String taxFree) {
		this.taxFree = taxFree;
	}
	public String getSearchPosit() {
		return searchPosit;
	}
	public void setSearchPosit(String searchPosit) {
		this.searchPosit = searchPosit;
	}
	public String getPayRegDate() {
		return payRegDate;
	}
	public void setPayRegDate(String payRegDate) {
		this.payRegDate = payRegDate;
	}
	public String getResDate() {
		return resDate;
	}
	public void setResDate(String resDate) {
		this.resDate = resDate;
	}
	public String getLawyerName() {
		return lawyerName;
	}
	public void setLawyerName(String lawyerName) {
		this.lawyerName = lawyerName;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
}
