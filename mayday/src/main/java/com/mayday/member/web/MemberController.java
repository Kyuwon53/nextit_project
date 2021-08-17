package com.mayday.member.web;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.xmlbeans.impl.jam.visitor.MVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mayday.common.vo.ResultMessageVO;
import com.mayday.exception.BizNotEffectedException;
import com.mayday.exception.BizNotFoundException;
import com.mayday.faq.vo.FaqSearchVO;
import com.mayday.login.service.ILoginService;
import com.mayday.member.service.IMemberService;
import com.mayday.member.vo.MemberSearchVO;
import com.mayday.member.vo.MemberVO;

@Controller
public class MemberController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Inject
	private IMemberService memberService;
	@Inject
	private ILoginService loginService;

	@RequestMapping("/member/memberList")
	public String memberList(@ModelAttribute("searchVO")MemberSearchVO searchVO,Model model) {
		logger.info("멤버 목록");
		logger.info("searchVO={}",searchVO);
		List<MemberVO> memberList = memberService.getMemberList(searchVO);
		model.addAttribute("memberList", memberList);
		model.addAttribute("searchVO", searchVO);
		return "member/memberList";
	}
	@RequestMapping("/member/memberView")
	public String getMember(@ModelAttribute("memId")String memId ,Model model) {
		logger.debug("memId={}",memId);
		try {
			MemberVO member = memberService.getMember(memId);
			model.addAttribute("member", member);
		} catch (BizNotFoundException e) {
			logger.debug("e={}",e);
			model.addAttribute("e", e);
		}
		return "member/memberView";
	}
	@RequestMapping("/member/modify")
	public String modify(@ModelAttribute("searchVO")MemberSearchVO searchVO,@ModelAttribute("member")MemberVO member ,Model model) {
		List<MemberVO> memberList = memberService.getMemberList(searchVO);
		ResultMessageVO resultMessage = new ResultMessageVO();
		try {
			memberService.Modify(member);
			resultMessage.setResult(true);
			resultMessage.setMessage("수정되었습니다.");
			resultMessage.setTitle("수정");
			resultMessage.setUrl("/member/memberView?boNo="+member.getMemId());
			resultMessage.setUrlTitle("해당 뷰");
		} catch (BizNotFoundException enf) {
			resultMessage.setResult(false);
			resultMessage.setMessage("실패: 해당 아이디를 찾지 못했습니다.");
			resultMessage.setTitle("수정");
			resultMessage.setUrl("/member/memberList");
			resultMessage.setUrlTitle("목록으로");
		} catch (BizNotEffectedException ene) {
			resultMessage.setResult(false);
			resultMessage.setMessage("실패: 수정 실패하였습니다.");
			resultMessage.setTitle("수정");
			resultMessage.setUrl("/member/memberView?boNo="+member.getMemId());
			resultMessage.setUrlTitle("목록으로");
		}
		model.addAttribute("memberList", memberList);
		return "redirect:/member/memberList";
	}
	@RequestMapping("/member/expertList")
	public String expertList(@ModelAttribute("searchVO")MemberSearchVO searchVO,Model model) {
		logger.debug("변호사 목록");
		logger.debug("searchVO={}",searchVO);
		if(searchVO.getRowSizePerPage()==10) searchVO.setRowSizePerPage(8);
		List<MemberVO> expertList = memberService.getExpertList(searchVO);
		model.addAttribute("expertList", expertList);
		model.addAttribute("searchVO", searchVO);
		return "member/expertList";
	}
	@RequestMapping("/member/expertView")
	public String getExpert(@ModelAttribute("memId")String memId ,Model model) {
		logger.debug("변호사 상세보기");
		try {
			MemberVO expert = memberService.getMember(memId);
			model.addAttribute("expert", expert);
		} catch (BizNotFoundException e) {
			model.addAttribute("e", e);
		}
		return "member/expertView";
	}
	
	@RequestMapping(value = "/lawyer/regist", method = RequestMethod.POST, params = "memId")
	public ModelAndView insertLawyer(MemberVO member) {
		logger.debug("member={}", member);
		ModelAndView mav = new ModelAndView("/login/login");
		ResultMessageVO resultMessage = new ResultMessageVO();
		try {
			memberService.registLawyer(member);
			resultMessage.setResult(true);
			resultMessage.setMessage("등록되었습니다.");
			resultMessage.setTitle("등록");
			resultMessage.setUrl("/member/memberView?boNo="+member.getMemId());
			resultMessage.setUrlTitle("해당 뷰");
		}  catch (BizNotEffectedException e) {
			resultMessage.setResult(false);
			resultMessage.setMessage("실패: 해당 아이디를 찾지 못했습니다.");
			resultMessage.setTitle("수정");
			resultMessage.setUrl("/member/memberList");
			resultMessage.setUrlTitle("목록으로");
		}
		return mav;
		
		
	}
	@RequestMapping(value = "/member/regist", method = RequestMethod.POST, params = "memId")
	public ModelAndView insertMember(MemberVO member) {
		logger.debug("member={}", member);
		ModelAndView mav = new ModelAndView("/login/login");
		ResultMessageVO resultMessage = new ResultMessageVO();
		try {
			memberService.registMember(member);
			resultMessage.setResult(true);
			resultMessage.setMessage("등록되었습니다.");
			resultMessage.setTitle("등록");
			resultMessage.setUrl("/member/memberView?boNo="+member.getMemId());
			resultMessage.setUrlTitle("해당 뷰");
		}  catch (BizNotEffectedException e) {
			resultMessage.setResult(false);
			resultMessage.setMessage("실패: 해당 아이디를 찾지 못했습니다.");
			resultMessage.setTitle("수정");
			resultMessage.setUrl("/member/memberList");
			resultMessage.setUrlTitle("목록으로");
		}
		return mav;
	}
	@ResponseBody
	@PostMapping(value = "/member/updateState",produces = "application/text; charset=UTF-8")
	public String updateState(MemberVO member) {
		try {
			loginService.updateMemState(member);
			return "변경되었습니다.";
		} catch (BizNotFoundException e) {
			return "회원 정보를 찾을 수 없습니다.";
		} catch (BizNotEffectedException e) {
			return "변경 실패";
		}
	}
	
	@PostMapping(value = "/member/passCheck",produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> passCheck(MemberVO member,String state){
		logger.debug("member={}, state={}",member,state);
		Map<String, Object> map = new HashedMap<String, Object>();
		try {
			memberService.passCheck(member);
			map.put("result", true);
			map.put("message", "비밀번호 확인 완료");
		} catch (BizNotFoundException e) {
			map.put("result", false);
			map.put("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		map.put("state", state);
		return map;
	}
	
	@PostMapping(value = "/member/leave",produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> leaveMember(MemberVO member,HttpServletRequest req) {
		logger.debug("member={}",member);
		HttpSession session =  req.getSession();
		Map<String, Object> map = new HashedMap<String, Object>();
		try {
			memberService.leaveMember(member);
			session.removeAttribute("USER_INFO");		
			map.put("result", true);
			map.put("message", "그동안 MAYDAY를 이용해주셔서 감사합니다.");
			map.put("url", "/");
		} catch (BizNotFoundException e) {
			map.put("result", false);
			map.put("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
			map.put("url", "/info/mypage");
		} catch (BizNotEffectedException e) {
			map.put("result", false);
			map.put("message", "탈퇴실패");
			map.put("url", "/info/mypage");
		}
		return map;
	}
	
	@PostMapping(value = "/member/changePW",produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> changePass(MemberVO member,HttpServletRequest req) {
		logger.debug("member={}",member);
		HttpSession session =  req.getSession();
		Map<String, Object> map = new HashedMap<String, Object>();
			try {
				memberService.changePassWord(member);
				map.put("result", true);
				map.put("message", "비밀번호가 변경되었습니다.");
				map.put("url", "/info/mypage");
			} catch (BizNotFoundException e) {
				map.put("result", false);
				map.put("message", "정보를 찾을 수 없습니다.");
				map.put("url", "/info/mypage");
			} catch (BizNotEffectedException e) {
				map.put("result", false);
				map.put("message", "비밀번호 변경 실패");
				map.put("url", "/info/mypage");
			}
		return map;
	}
	
	@RequestMapping(value = "/member/{positCd}/excelDown")
	@ResponseBody
	public void excelDown(@ModelAttribute("searchVO") MemberSearchVO searchVO, @PathVariable("positCd") String positCd,
			HttpServletResponse resp, HttpServletRequest req) throws Exception {
		String title = "";
		if (positCd.equals("expert")) {
			title = "변호사";
			memberService.excelDown(searchVO,positCd,title, resp);
		} else if (positCd.equals("counsel")) {
			title = "의뢰인";
			memberService.excelDown(searchVO,positCd,title, resp);
		} 
	}
}
