package com.mayday.signup.web;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mayday.common.valid.LaywerSignup1;
import com.mayday.common.valid.LaywerSignup2;
import com.mayday.common.valid.MemberSignup1;
import com.mayday.common.valid.MemberSignup2;
import com.mayday.common.vo.ResultMessageVO;
import com.mayday.exception.BizNotEffectedException;
import com.mayday.exception.BizNotFoundException;
import com.mayday.login.service.ILoginService;
import com.mayday.member.service.IMemberService;
import com.mayday.member.service.MailSendService;
import com.mayday.member.vo.MemberVO;

@Controller
@SessionAttributes("member")
public class SignupController {
	@ModelAttribute("member")
	public MemberVO member() {
		MemberVO member = new MemberVO();
		return member;
	}

	@Inject
	IMemberService memberService;
	@Inject
	ILoginService loginService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/signup/signup")
	public String signup(@ModelAttribute("member") MemberVO member) {
		return "signup/signup";
	}

	@RequestMapping(value = "/signup/signup1")
	public String lawyerSignup1() {
		return "signup/signup1";
	}

	@RequestMapping(value = "/signup/signup1-2")
	public String memberSignup1() {
		
		return "signup/signup1-2";
	}

	@RequestMapping(value = "/signup/signup2")
	public String lawyerSignup2(@ModelAttribute("member") @Validated(LaywerSignup1.class) MemberVO member,
			BindingResult errors, Model model) {
		logger.info("member={}", member);
		if (errors.hasErrors()) {
			return "signup/signup1";
		}
		return "signup/signup2";
	}

	@RequestMapping(value = "/signup/signup2-2")
	public String memberSignup2(@ModelAttribute("member") @Validated(MemberSignup1.class) MemberVO member,
			BindingResult errors, Model model) {
		logger.info("member={}", member);
		if (errors.hasErrors()) {
			return "signup/signup1-2";
		}
		return "signup/signup2-2";
	}

	@RequestMapping(value = "/signup/registMember")
	public String memberRegist(@ModelAttribute("member") @Validated(MemberSignup2.class) MemberVO member,
			BindingResult errors, Model model) {
		logger.info("member={}", member);
		if (errors.hasErrors()) {
			return "signup/signup2-2";
		}
		try {
			memberService.registMember(member);
		} catch (BizNotEffectedException e) {
			e.printStackTrace();
		}
		return "home";
	}

	@RequestMapping(value = "/signup/registLawyer")
	public String lawyerRegist(@ModelAttribute("member") @Validated(LaywerSignup2.class) MemberVO member,
			BindingResult errors, Model model) {
		logger.info("member={}", member);
		ResultMessageVO resultMessage = new ResultMessageVO();
		if (errors.hasErrors()) {
			return "signup/signup2";
		}
		try {
			memberService.registLawyer(member);
		} catch (BizNotEffectedException e) {
			resultMessage.setResult(false);
		}
		return "home";
	}

	@PostMapping("/signup/idVal")
	@ResponseBody
	public String idVal(@RequestParam String id) {
		try {
			String res = "success";
			if(memberService.getMember(id).getMemId() == null) {
				res = "fail";
			}
			return res;
		}catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		
		
	}
	
	@Inject
	MailSendService mss;
	
	@RequestMapping("/signup/mail")
	@ResponseBody // ajax에서 데이터 받을 때 , return 값을 view 찾는 것이 아니라 ajax의 success 함수의 매개변수로
	public String mail(@RequestParam String mail) {
		String random = mss.sendAuthMail(mail);
		return random;
	}
	
	@RequestMapping("/signup/findid")
	public String findId() {
		
		return "signup/findid";
	}
	
	@RequestMapping("/signup/findpw")
	public String findPw() {
		
		return "signup/findpw";
	}
	
	@RequestMapping("/signup/resultid")
	public String resultId(String memMail,Model model) throws BizNotFoundException{
		ResultMessageVO messageVO = new ResultMessageVO();
		try {
			String id = loginService.whereMyId(memMail);
			messageVO.setResult(true);
			messageVO.setMessage(id.replaceAll("(.{2})..(.*)", "$1**$2"));
		}catch(BizNotFoundException e) {
			messageVO.setResult(false);
			messageVO.setMessage("해당 메일이 존재하지 않습니다.");
		}
		 
		model.addAttribute("messageVO",messageVO);
		return "signup/resultid";
	}
	
	@RequestMapping("/signup/resultpw")
	public String resultPw(MemberVO member,Model model) {
		ResultMessageVO messageVO = new ResultMessageVO();		
		try {
			String pass = loginService.whereMyPass(member);
			messageVO.setResult(true);
			messageVO.setMessage(pass.replaceAll("(.{2})..(.*)", "$1**$2"));
		} catch (BizNotFoundException e) {
			messageVO.setResult(false);
			messageVO.setMessage("해당 아이디 또는 메일이 존재하지 않습니다.");
		} 
		
		model.addAttribute("messageVO", messageVO);		
		return "signup/resultpw";
		
	}

}
