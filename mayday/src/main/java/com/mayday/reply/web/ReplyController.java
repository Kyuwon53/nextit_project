package com.mayday.reply.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mayday.login.vo.UserVO;
import com.mayday.reply.service.IReplyService;
import com.mayday.reply.vo.ReplySearchVO;
import com.mayday.reply.vo.ReplyVO;
import com.mayday.replylike.service.IReplyLikeService;
import com.mayday.replylike.vo.ReplyLikeVO;

@Controller
public class ReplyController {
	@Inject
	private IReplyService replyService;
	@Inject
	private IReplyLikeService replylikeservice;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@ResponseBody
	@RequestMapping(value = "/reply/replyList", produces = "application/json;charset=UTF-8")
	public Map<String, Object> replyLisy(ReplySearchVO searchVO, HttpServletRequest req, Model model) {
		logger.info("ReplySearchVO={}", searchVO);
		HttpSession session = req.getSession();
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
		Map<String, Object> map = new HashMap<String, Object>();
		if (user != null) {
			ReplyLikeVO memLike = new ReplyLikeVO();
			memLike.setMemId(user.getUserId());
			memLike.setBoNo(searchVO.getReParentNo());
			List<ReplyLikeVO> memLikeList = replylikeservice.getreplyLike(memLike);
			map.put("memLikeList", memLikeList);
		} else {
			map.put("memLikeList", 0);
		}
		List<ReplyVO> list = replyService.getReplyList(searchVO);
		map.put("result", true);
		map.put("data", list);
		map.put("count", list.size());
		map.put("searchVO", searchVO);

		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/reply/replyRegist", method = RequestMethod.POST)
	public Map<String, Object> replyRegist(ReplyVO reply, HttpServletRequest req, HttpSession session) {
		logger.info("ReplyVO={}", reply);
		reply.setReIp(req.getRemoteAddr());
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
		reply.setReMemId(user.getUserId());
		replyService.registReply(reply);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		map.put("msg", "정상 등록 되었습니다.");
		session.removeAttribute("reContent");
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/reply/replyModify")
	public Map<String, Object> replyModify(ReplyVO reply, HttpSession session) {
		logger.info("ReplyVO={}", reply);
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
		reply.setReMemId(user.getUserId());
		Map<String, Object> map = new HashMap<String, Object>();
		replyService.modifyReply(reply);
		map.put("result", true);
		map.put("msg", "수정 되었습니다.");
		return map;

	}

	// delete
	@ResponseBody
	@RequestMapping(value = "/reply/replyDelete")
	public Map<String, Object> replyDelete(ReplyVO reply, HttpSession session) {
		logger.info("ReplyVO={}", reply);
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
		reply.setReMemId(user.getUserId());
		Map<String, Object> map = new HashMap<String, Object>();
		replyService.removeReply(reply);
		map.put("result", true);
		map.put("msg", "삭제 되었습니다.");
		return map;

	}
}
