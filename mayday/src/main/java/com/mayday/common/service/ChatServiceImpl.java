package com.mayday.common.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mayday.common.vo.ChatMessageVO;
import com.mayday.common.vo.ChatRoomVO;
import com.mayday.exception.BizNotEffectedException;
import com.mayday.exception.BizNotFoundException;
@Service
public class ChatServiceImpl implements IChatService{
	
	@Inject
	IChatDao chatDao;
	
	@Override
	public List<ChatRoomVO> getChatRoomList(String userId) {
		return chatDao.getChatRoomList(userId);
	}
	@Override
	public List<ChatMessageVO> getChatMessageList(String roomId) {
		return chatDao.getChatMessage(roomId);
	}

	@Override
	public ChatRoomVO getChatRoomById(String roomId){
		return chatDao.getChatRoom(roomId);
	}
	@Override
	public String getChatRoomId(ChatRoomVO chatRoom) {
		return chatDao.getChatRoomId(chatRoom);
	}
	@Override
	public void registChatRoom(ChatRoomVO chatRoom)throws BizNotEffectedException {
		int cnt = chatDao.createChatRoom(chatRoom);
		if(cnt<1) throw new BizNotEffectedException();
	}

	@Override
	public void insertChatMessage(ChatMessageVO chatMessage)throws BizNotEffectedException {
		int cnt = chatDao.insertChatMessage(chatMessage);
		if(cnt<1) throw new BizNotEffectedException();		
	}

	@Override
	public void removeChatRoom(String roomId) throws BizNotEffectedException{
		int cnt = chatDao.deleteChatRoom(roomId);
		if(cnt<1) throw new BizNotEffectedException();		
	}

	@Override
	public void removeChatMessage(String roomId)throws  BizNotEffectedException {
		int cnt = chatDao.deleteChatMessage(roomId);
		if(cnt<1) throw new BizNotEffectedException();		
	}
	@Override
	public void updateChatRoomStart(String roomId) throws BizNotFoundException, BizNotEffectedException  {
		ChatRoomVO vo = chatDao.getChatRoom(roomId);
		if(vo == null) throw new BizNotFoundException();
		int cnt = chatDao.updateChatRoomStart(roomId);
		if(cnt < 1) throw new BizNotEffectedException();
	}
	@Override
	public void updateChatRoomStop(String roomId)throws BizNotFoundException, BizNotEffectedException  {
		ChatRoomVO vo = chatDao.getChatRoom(roomId);
		if(vo == null) throw new BizNotFoundException();
		int cnt = chatDao.updateChatRoomStop(roomId);
		if(cnt < 1) throw new BizNotEffectedException();
	}
}
