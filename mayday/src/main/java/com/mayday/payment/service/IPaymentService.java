package com.mayday.payment.service;

import java.util.List;

import com.mayday.exception.BizNotEffectedException;
import com.mayday.payment.vo.PaymentVO;

public interface IPaymentService {
	
	public List<PaymentVO> getPaymentListByMemId(PaymentVO payment);
	public Integer countPaymentTotal(PaymentVO payment);
	public void registPayment(PaymentVO payment) throws  BizNotEffectedException;
}
