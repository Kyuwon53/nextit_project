package com.mayday.common.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mayday.common.vo.ChatMessageVO;
import com.mayday.common.vo.ChatRoomVO;

@Mapper
public interface IChatDao {
	// 사용자 채팅방 리스트 
	public List<ChatRoomVO> getChatRoomList(String userId);
	// 채팅리스트
	public List<ChatMessageVO> getChatMessage(String roomId);
	// 채팅방 불러오기
	public ChatRoomVO getChatRoom(String roomId);
	// 클라이언트, 변호사에 맞는 roomId 불러오기 
	public String getChatRoomId(ChatRoomVO chatRoom);
	// 채팅방개설
	public int createChatRoom(ChatRoomVO chatRoom);
	// 메세지 저장
	public int insertChatMessage(ChatMessageVO chatMessage);
	// 채팅방 활성화
	public int updateChatRoomStart(String roomId);
	// 채팅방 비활성화
	public int updateChatRoomStop(String roomId);
	// 채팅방 삭제 
	public int deleteChatRoom(String roomId);
	// 메세지 삭제
	public int deleteChatMessage(String roomId);
}
