package com.mayday.payment.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mayday.exception.BizNotEffectedException;
import com.mayday.payment.dao.IPaymentDao;
import com.mayday.payment.vo.PaymentVO;
@Service
public class PaymentService implements IPaymentService{
	@Inject
	private IPaymentDao paymentdao;
	
	@Override
	public List<PaymentVO> getPaymentListByMemId(PaymentVO payment) {
		return paymentdao.paymentListByMemId(payment);
	}
	@Override
	public Integer countPaymentTotal(PaymentVO payment) {
		return paymentdao.paymentTotalCount(payment);
	}
	public void registPayment(PaymentVO payment) throws BizNotEffectedException {
		int cnt = paymentdao.insertPayment(payment);
		if(cnt<1) throw new BizNotEffectedException();
		
	}
}
