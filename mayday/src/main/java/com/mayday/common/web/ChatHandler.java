package com.mayday.common.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.mayday.common.service.IChatService;
import com.mayday.common.vo.ChatRoomVO;
import com.mayday.login.vo.UserVO;

@Controller
public class ChatHandler extends TextWebSocketHandler implements InitializingBean {
	@Inject
	IChatService chatService;
	//로그인 중인 유저
	private Map<String, WebSocketSession> users = new ConcurrentHashMap<String, WebSocketSession>();
	
	private Map<String, List<WebSocketSession>> counselMap = new HashMap<String, List<WebSocketSession>>();
	// session, 방 번호 
	private static List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static int i;
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
		String senderId = getMemberId(session);
		logger.info("senderId = {}",senderId);
		
		String msg = message.getPayload();
		logger.info("msg={}",msg);
		if(msg!= null) {
			String[] strs = msg.split(",");
			if(strs != null) {
				String memName = strs[0];
				String memId = strs[1];
				String content = strs[2];
				String roomId = strs[3];
				String state = strs[4];
				TextMessage mess = new TextMessage(memName+":"+content+":"+state+":"+roomId);
				// 현재 roomId에 해당하는 목록 구하고 
				// 목록에 나의 세션이 없다면 추가 
				// 목록에 상대의 세션이 존재하는 확인 
				List<WebSocketSession> counselList = counselMap.get(roomId);
				// 해당 목록이 존재하지 않으면 생성 
				if(counselList == null) {
						logger.info("{}에 해당하는 상담목록을 없어서 생성합니다.", roomId);
						counselList = new ArrayList<WebSocketSession>();
						counselMap.put(roomId, counselList);
				}
				// 내 세션이 목록에 존재하지 않으면 추가 
				if(!counselList.contains(session)) {
					logger.info("{}에 현재 세션을 채팅 삼담목록에 참여를 합니다.", roomId);
					counselList.add(session);
				}
				// 카운스 리스트에 잇는 모든 세션에 메시지 보내기 
				for(WebSocketSession s : counselList) {
					s.sendMessage(mess);
				}
			}
		}
		logger.info("{}:메세지 전송 msg={}",session.getId(),msg);
	}
	
	// client 접속 시 호출
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//		i++;
		String senderId = getMemberId(session);
		logger.info("senderId={}",senderId);
		if(senderId!=null) {
			logger.info(session.getId()+"클라이언트 접속 총 접속 인원:" +i);
			users.put(senderId, session);
			sessionList.add(session);
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		logger.info(session.getId()+"클라이언트 접속 해제 총 접속 인원 : " + i);
		//users.remove(session);
		//sessionList.remove(session);
		// TODO : 1:1 채팅맵에 참여중인 리스트이 갯수가 0 이라면 삭제 
	}
	
	private String getMemberId(WebSocketSession session) {
		Map<String, Object> httpSession = session.getAttributes();
		UserVO user = (UserVO) httpSession.get("USER_INFO");
		String senderId = user.getUserId();
		return senderId == null ? null: senderId;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
	
		
	}
}
