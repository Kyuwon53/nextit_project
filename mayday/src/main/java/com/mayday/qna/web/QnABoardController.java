package com.mayday.qna.web;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mayday.common.vo.ResultMessageVO;
import com.mayday.exception.BizNotEffectedException;
import com.mayday.exception.BizNotFoundException;
import com.mayday.login.vo.UserVO;
import com.mayday.qna.service.IQnABoardService;
import com.mayday.qna.vo.QnASearchVO;
import com.mayday.qna.vo.QnAVO;

@Controller
public class QnABoardController {

	@Inject
	IQnABoardService qnaBoardService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 1대1 문의게시판에 리스트 보이기 ,관리자, 의뢰인, 변호사/노무사 다르게 보이게  
	@RequestMapping("/qna/qnaList")
	public String qnaList(@ModelAttribute("searchVO") QnASearchVO searchVO,HttpServletRequest req,Model model) {
		logger.info("searchVO={}", searchVO);
		HttpSession session = req.getSession();
		UserVO user = (UserVO)session.getAttribute("USER_INFO");
		logger.info("user={}", user);
		if(user == null) {
			return "redirect:/login/login";
		}
		if(user.getUserRole().contains("CLIENT")) {
			List<QnAVO> qnaBoardList = qnaBoardService.getQnAList(user.getUserId());
			model.addAttribute("qnaBoardList", qnaBoardList);
		}else {
			List<QnAVO> qnaBoardList = qnaBoardService.getList(searchVO);
			model.addAttribute("qnaBoardList", qnaBoardList);
		}
		return "qna/qnaList";
	}
	// 상세보기 UserVO user = (UserVO)session.getAttribute("USER_INFO");
	// user role이 client이면 checkQna하기 
	@RequestMapping("/qna/qnaView")
	public String qnaView(@ModelAttribute("qna")QnAVO qna ,@RequestParam("boNo")int boNo,HttpServletRequest req ,Model model) {
		logger.info("boNo={}", boNo);
		qna = qnaBoardService.getQnABoard(boNo);
		HttpSession session = req.getSession();
		UserVO user = (UserVO)session.getAttribute("USER_INFO");
		String role = user.getUserRole();
		System.out.println(role);
		if(role.contains("CLIENT")) {
			if(qna.getGroupLayer()==1) {
				qna.setBoCheck("Y");
				qnaBoardService.checkQna(qna);
			}
		}
		model.addAttribute("qna", qna);
		return "qna/qnaView";
	}
	@RequestMapping("/qna/qnaEdit")
	public String qnaEdit(@RequestParam(value = "boNo", required = true) int boNo,Model model) {
		logger.info("boNo={}",boNo);
		QnAVO qna = qnaBoardService.getQnABoard(boNo);
		model.addAttribute("qna", qna);
		return "qna/qnaEdit";
	}
	// 수정
	@PostMapping("/qna/qnaModify")
	public String qnaModify(@ModelAttribute("qna") QnAVO qna,Model model) {
		logger.info("qna", qna);
		ResultMessageVO resultMessage = new ResultMessageVO(); 
		try {
			qnaBoardService.modifyQnABoard(qna);
			model.addAttribute("qna", qna);
		} catch (BizNotFoundException e) {
			resultMessage.setResult(false);
			resultMessage.setTitle("수정");
			resultMessage.setMessage("글을 찾을 수 없습니다.");
			resultMessage.setUrlTitle("목록");
			resultMessage.setUrl("/qna/qnaList");
			resultMessage.setCode("404");
			model.addAttribute("e", e);
		} catch (BizNotEffectedException e) {
			resultMessage.setResult(false);
			resultMessage.setTitle("수정");
			resultMessage.setMessage("수정 실패하였습니다.");
			resultMessage.setUrlTitle("목록");
			resultMessage.setUrl("/qna/qnaList");
			resultMessage.setCode("400");
		}
		model.addAttribute("resultMessage", resultMessage);
		return "qna/qnaView";
	}
	
	@RequestMapping(value = "/qna/qnaDelete" ,produces = "application/text; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> qnaDelete(@RequestParam("boNo")int boNo,
								@RequestParam("groupLayer")int groupLayer,
								@RequestParam("boCheck")String boCheck) {
		logger.info("boNo={}", boNo);
		logger.info("groupLayer={}", groupLayer);
		QnAVO qna = qnaBoardService.getQnABoard(boNo);
		Map<String, Object> map = new HashedMap<String, Object>();
		try {
			qnaBoardService.removeQnABoard(boNo);
			if(groupLayer == 0) { //원글
				qnaBoardService.removeAnsBoard(boNo);
			}else { // 답글
				QnAVO original = qnaBoardService.getQnABoard(qna.getOriginalNo());
				original.setBoCheck("N");
				qnaBoardService.checkQna(original);
			}
			map.put("result", true);
			map.put("message", "삭제되었습니다.");
		} catch (BizNotFoundException e) {
			map.put("result", false);
			map.put("message", "글을 찾지 못했습니다.");
		} catch (BizNotEffectedException e) {
			map.put("result", false);
			map.put("message", "삭제 실패했습니다.");
		}
		return map;
	}
	
	@RequestMapping("/qna/qnaForm")
	public String qnaForm(@ModelAttribute("qna")QnAVO qna, Model model) {
		logger.info("qna={}", qna);
		return "qna/qnaForm";
	}
	@PostMapping("/qna/{cate}/qnaRegist")
	public String qnaRegist(@ModelAttribute("qna") QnAVO qna, @PathVariable("cate")String cate,Model model) {
		logger.info("cate={}", cate);
		logger.info("qna={}", qna);
		ResultMessageVO resultMessage = new ResultMessageVO();
		if(cate.equals("question")) {
			try {
				qnaBoardService.registQnABoard(qna);
				resultMessage.setResult(true);
				resultMessage.setMessage("등록 됐습니다.");
				resultMessage.setTitle("등록 성공");
				resultMessage.setUrl("/qna/qnaList");
				resultMessage.setUrlTitle("게시판으로");
			} catch (BizNotEffectedException e) {
				resultMessage.setResult(false);
				resultMessage.setMessage("등록에 실패했습니다.");
				resultMessage.setTitle("등록실패");
				resultMessage.setUrl("/qna/qnaList");
				resultMessage.setUrlTitle("게시판으로");
			}
		}else if(cate.equals("answer")) {
			try {
				qnaBoardService.registAnsBoard(qna);
				QnAVO client = qnaBoardService.getQnABoard(qna.getBoNo());
				client.setAnsMemId(qna.getMemId());
				try {
					qnaBoardService.modifyQnABoard(client);
				} catch (BizNotFoundException e)  {
					logger.debug("e={}",e);
				}
				qna.setBoCheck("Y");
				qnaBoardService.checkQna(qna);
				resultMessage.setResult(true);
				resultMessage.setMessage("등록 됐습니다.");
				resultMessage.setTitle("등록 성공");
				resultMessage.setUrl("/qna/qnaList");
				resultMessage.setUrlTitle("게시판으로");
			} catch (BizNotEffectedException e) {
				resultMessage.setResult(false);
				resultMessage.setMessage("등록에 실패했습니다.");
				resultMessage.setTitle("등록실패");
				resultMessage.setUrl("/qna/qnaList");
				resultMessage.setUrlTitle("게시판으로");
			}
		}
		model.addAttribute("resultMessage", resultMessage);
		return "common/message";
	}
}
