package com.mayday.common.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mayday.common.service.IChatService;
import com.mayday.common.vo.ChatMessageVO;
import com.mayday.common.vo.ChatRoomVO;
import com.mayday.exception.BizNotEffectedException;
import com.mayday.exception.BizNotFoundException;
import com.mayday.login.vo.UserVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
	@Inject
	private IChatService chatService; 
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	//변호사 채팅방 이동 
	@RequestMapping(value = "/chatExpert")
	public String chatExpert(HttpServletRequest req ,Model model) {
		HttpSession session = req.getSession();
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
		List<ChatRoomVO> chatRoomList = chatService.getChatRoomList(user.getUserId());
		model.addAttribute("chatRoomList", chatRoomList);
		return "chat/chatExpert";
	}
	// 채팅방 리스트
	@ResponseBody
	@RequestMapping(value = "/chatRoomList")
	public Map<String, Object> roomList(HttpServletResponse resp,HttpServletRequest req ,Model model ) {
		HttpSession session = req.getSession();
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<ChatRoomVO> chatRoomList = chatService.getChatRoomList(user.getUserId());
		map.put("result", true);
		map.put("roomList", chatRoomList);
		map.put("count", chatRoomList.size());
		//resp.setContentType("application/json; charset=utf-8");
		return map;
	}
	@ResponseBody
	@RequestMapping(value = "/chatRoom")
	public Map<String, Object> messageList(String roomId){
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<ChatMessageVO> chatMessageList = chatService.getChatMessageList(roomId);
		ChatRoomVO chatRoom = chatService.getChatRoomById(roomId);
		map.put("result", true);
		map.put("messageList", chatMessageList);
		map.put("chatRoom", chatRoom);
		return map;
	}
	
	@RequestMapping(value="/{roomId}")
	public void massageList(@PathVariable String roomId,HttpServletRequest req,Model model, HttpServletResponse resp) {
		
		List<ChatMessageVO> chatMessageList = chatService.getChatMessageList(roomId);
		ChatRoomVO chatRoom = chatService.getChatRoomById(roomId);
		model.addAttribute("chatRoom", chatRoom);
		
		resp.setContentType("application/json; charset=utf-8");
		
		ChatMessageVO chatMessage = new ChatMessageVO();
		chatMessage.setMessage(req.getParameter("message"));    
		chatMessage.setMemId(req.getParameter("memId"));      
		chatMessage.setMemName(req.getParameter("memName"));
		chatMessage.setRoomId(roomId);
		model.addAttribute("chatMessageList", chatMessageList);
	}
	
	// 채팅방 개설
	@ResponseBody
	@PostMapping(value = "/createroom")
	public String createRoom(HttpServletRequest req) {
		String	 roomId= UUID.randomUUID().toString();
		String	 userId = req.getParameter("userId") ;     
		String	 userName= req.getParameter("userName") ;   
		String	 expertId= req.getParameter("expertId") ;   
		String	 expertName = req.getParameter("expertName") ;
		ChatRoomVO chatRoom = new ChatRoomVO();
		chatRoom.setRoomId(roomId);
		chatRoom.setUserId(userId);
		chatRoom.setUserName(userName);
		chatRoom.setExpertId(expertId);
		chatRoom.setExpertName(expertName);
		String exist = chatService.getChatRoomId(chatRoom);
		try {
			if (exist == null) { //해당 변호사와 유저와 방이 없으면 새로개설
				chatService.registChatRoom(chatRoom);
				HttpSession session = req.getSession();
				session.setAttribute("CHAT_ROOM", chatRoom);
				return roomId;
			} else {	// 해당 변호사와 유저와 방이 이미 존재한다면 해당 방번호 리턴
				try {
					chatService.updateChatRoomStart(exist);
				} catch (BizNotFoundException e) {
					return "fail";
				}
				HttpSession session = req.getSession();
				session.setAttribute("CHAT_ROOM", chatService.getChatRoomById(exist));
				return exist;
			}
		} catch (BizNotEffectedException e) {
			return "fail";
		}
	}
	
	@ResponseBody
	@PostMapping(value = "/saveChat")
	public String saveChat(ChatMessageVO chatMessage, HttpServletRequest req, HttpSession session) {
		chatMessage.setMemId(req.getParameter("memId"));
		chatMessage.setMemName(req.getParameter("memName"));
		chatMessage.setMessage(req.getParameter("message"));
		chatMessage.setRoomId(req.getParameter("roomId"));
		try {
			chatService.insertChatMessage(chatMessage);
			return "success";
		} catch (BizNotEffectedException e) {
			return "fail : "+e;
		}
	}
	@ResponseBody
	@PostMapping(value = "/disconnect")
	public String disconnect(String roomId) {
		try {
			chatService.updateChatRoomStop(roomId);
			return "/";
		} catch (BizNotFoundException e) {
			logger.info("e={}",e);
			return "redirect:chat/chatLogin";
		} catch (BizNotEffectedException e) {
			logger.info("e={}",e);
			return "redirect:chat/chatLogin";
		}
	}
}
