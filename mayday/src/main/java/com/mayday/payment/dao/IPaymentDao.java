package com.mayday.payment.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mayday.payment.vo.PaymentVO;
@Mapper
public interface IPaymentDao {
	// 결제내역 리스트 
	public List<PaymentVO> paymentListByMemId (PaymentVO payment);
	// 총 금액 
	public Integer paymentTotalCount(PaymentVO payment);
	public int insertPayment(PaymentVO payment);
	
}
