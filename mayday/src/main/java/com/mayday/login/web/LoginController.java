package com.mayday.login.web;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mayday.common.util.CookieUtil;
import com.mayday.exception.BizNotEffectedException;
import com.mayday.exception.BizNotFoundException;
import com.mayday.exception.BizPasswordNotMatchedException;
import com.mayday.login.service.ILoginService;
import com.mayday.login.vo.UserVO;
import com.mayday.member.service.IMemberService;
import com.mayday.member.vo.MemberVO;

@Controller
public class LoginController {
	
	@Inject
	private ILoginService loginService;
	@Inject
	private IMemberService memberService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping(value = "/login/login")
	public String getLogin() {
		
		return "login/login";
	}	
	
	@PostMapping(value = "/login/login")
	public String postLogin(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		String id = req.getParameter("userId");
		String pass = req.getParameter("userPass");
		String saveId = req.getParameter("saveId");
		logger.info("id = {}, pw = {},saveId = {}", id,pass,saveId);
		
		if (saveId == null) {
			CookieUtil cookieUtil = new CookieUtil(req);
			if (cookieUtil.exists("SAVED_ID")) {
				resp.addCookie(CookieUtil.createCookie("SAVED_ID", id, "/", 0));
			}
			saveId = "";
		}
		if (saveId.equals("Y")) {
			resp.addCookie(CookieUtil.createCookie("SAVED_ID", id, "/", 3600 * 24 * 7));
		}
		if ((id.isEmpty() || id == null) || (pass.isEmpty() || pass == null)) {
			return "login/login";
		}
		try {
			UserVO user = loginService.login(id, pass);
			HttpSession session = req.getSession();
			session.setAttribute("USER_INFO", user);
			session.setMaxInactiveInterval(1800);
			MemberVO member = memberService.getMember(user.getUserId());
			member.setMemState("L");
			loginService.updateMemState(member);						
			String prePa = (String)session.getAttribute("preP");
			String prePage = (String) session.getAttribute("PREPAGE");
			session.removeAttribute("PREPAGE");
			session.removeAttribute("preP");
			
			logger.debug("로그인에 성공해서 두 값은 삭제.  PREPAGE={}, preP={}", prePage , prePa );
			
			if(prePage == null && prePa == null) {
				return "redirect:/";
			}else {
				if(prePage != null) {
					String reContent = (String) session.getAttribute("reContent");
					// session.removeAttribute("reContent");
					if(reContent!=null) {
						return "redirect:"+prePage+"&reContentYN=Y";
					}else {
						return "redirect:"+prePage;
					}
				}else if(prePa != null) {
					return "redirect:"+prePa;
				}else {
					return "redirect:/";
				}
			}
		} catch (BizNotFoundException fe) {
			model.addAttribute("fe", fe);
			return "login/login";
		} catch (BizPasswordNotMatchedException pe) {
			model.addAttribute("pe", pe);
			return "login/login";
		} catch(BizNotEffectedException ee) {
			model.addAttribute("ee", ee);
			return "login/login";
		}
	}
	
	@RequestMapping("/login/logout")
	public String logout(HttpServletRequest req) {
		HttpSession session =  req.getSession();
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
		try {
			MemberVO member = memberService.getMember(user.getUserId());
			member.setMemState("N");
			try {
				loginService.updateMemState(member);
			} catch (BizNotFoundException e) {
				return "redirect:/";
			} catch (BizNotEffectedException e) {
				return "redirect:/";
			}
		} catch (BizNotFoundException e1) {
			return "redirect:/";
		}
		session.removeAttribute("USER_INFO");		
		return "redirect:/";
	}
	
	@RequestMapping("/floatingmenu/floating")
	public String floating() {
		return "floatingmenu/floating";
	}
	
	@RequestMapping("/chat/chatLogin")
	public String chatLogin(HttpServletRequest req) {
		HttpSession session = req.getSession();
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
		if (user == null) {
			return "redirect:/login/login";
		}
		return "chat/chat";
	}
}
