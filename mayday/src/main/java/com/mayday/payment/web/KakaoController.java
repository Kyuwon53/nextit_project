package com.mayday.payment.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayday.exception.BizNotEffectedException;
import com.mayday.payment.dao.IPaymentDao;
import com.mayday.payment.service.IPaymentService;
import com.mayday.payment.vo.PaymentVO;

@RestController
public class KakaoController {

	@Inject
	private IPaymentService paymentservice;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/kakao")
	public String kakao(HttpServletRequest request) {
		try {
			String memId = request.getParameter("ID");
			String quantity = request.getParameter("quantity");
			String totalAmount = request.getParameter("totalAmount");
			String itemName = request.getParameter("itemName");
			String lawyerId = request.getParameter("lawyerId");
			URL url = new URL("https://kapi.kakao.com/v1/payment/ready");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "KakaoAK ce20c95432c7f41d7e76e28e9e729e87");
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			conn.setDoOutput(true);
			String params = "cid=TC0ONETIME&partner_order_id=MAYDAY&partner_user_id=" + memId + "&item_name=" + itemName
					+ "&quantity=" + quantity + "&total_amount=" + totalAmount
					+ "&tax_free_amount=0&approval_url=http://192.168.1.49:7001/success&fail_url=http://192.168.1.49:7001/failpayment&cancel_url=http://192.168.1.49:7001/cancelpayment";
			OutputStream out = conn.getOutputStream();
			DataOutputStream dout = new DataOutputStream(out);

			dout.writeBytes(params);
			dout.close();

			int result = conn.getResponseCode();

			InputStream in;
			if (result == 200) {
				in = conn.getInputStream();
			} else {
				in = conn.getErrorStream();
			}
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader bfr = new BufferedReader(isr);
			return bfr.readLine();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "{\"result\":\"NO\"}";
	}

	@RequestMapping(value = "/successpayment")
	public String successPayment(@ModelAttribute("payment") PaymentVO payment, HttpServletRequest request) {
		logger.info("payment={}",payment);
		payment.setMemId(request.getParameter("ID"));
		payment.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		payment.setTotalAmount(Integer.parseInt(request.getParameter("totalAmount")));
		payment.setItemName(request.getParameter("itemName"));
		payment.setLawyerId(request.getParameter("lawyerId"));
		payment.setResDate(request.getParameter("resDate"));
		try {
			paymentservice.registPayment(payment);
		} catch (BizNotEffectedException e) {
			e.printStackTrace();
		}
		return "{\"url\":\"/successpaymentdisplay\"}";
	}

}