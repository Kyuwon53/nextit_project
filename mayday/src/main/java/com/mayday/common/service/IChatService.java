package com.mayday.common.service;

import java.util.List;

import com.mayday.common.vo.ChatMessageVO;
import com.mayday.common.vo.ChatRoomVO;
import com.mayday.exception.BizNotEffectedException;
import com.mayday.exception.BizNotFoundException;

public interface IChatService {
	// 채팅방리스트, 채팅 메시지 리스트 
	public List<ChatRoomVO> getChatRoomList(String userId);
	public List<ChatMessageVO> getChatMessageList(String roomId);
	// roomId에 해당하는 채팅방
	public ChatRoomVO getChatRoomById(String roomId);
	// userId에 해당하는 방아이디 
	public String getChatRoomId(ChatRoomVO chatRoom);
	
	// 채팅방 상태 업데이트
	public void updateChatRoomStart(String roomId)throws BizNotFoundException, BizNotEffectedException;
	public void updateChatRoomStop(String roomId)throws BizNotFoundException, BizNotEffectedException;
	
	public void registChatRoom(ChatRoomVO chatRoom) throws BizNotEffectedException;
	public void insertChatMessage(ChatMessageVO chatMessage)throws BizNotEffectedException;
	public void removeChatRoom(String roomId)throws BizNotFoundException,BizNotEffectedException;
	public void removeChatMessage(String roomId)throws BizNotFoundException,BizNotEffectedException;
}
