package com.mayday.payment.web;


import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mayday.exception.BizNotFoundException;
import com.mayday.login.vo.UserVO;
import com.mayday.member.service.IMemberService;
import com.mayday.member.vo.MemberVO;

@Controller
public class PaymentController {
	@Inject
	private IMemberService memberService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
		
	@RequestMapping("/kakaoPay")
	public String kakao(@ModelAttribute("pay") MemberVO pay,HttpServletRequest req ,Model model) {
		logger.info("pay={}",pay);
		HttpSession session = req.getSession();
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
		try {
			MemberVO member = memberService.getMember(user.getUserId());
			model.addAttribute("member",member);
		} catch (BizNotFoundException e) {
			logger.error("e={}",e);
		}
		return "payment/kakaoPay";
	}

	@RequestMapping("/cancelpayment")
	public String cancelPayment() {
		return "{\"result\":\"NO\"}";
	}
	@RequestMapping("/successpaymentdisplay")
	public String successPaymentDisplay() {
		return "payment/successpayment";
	}

	@RequestMapping("/success")
	public String successPayment() {
		return "payment/success";
	}
	public String failPayment() {
		return "payment/failpayment";
	}

}