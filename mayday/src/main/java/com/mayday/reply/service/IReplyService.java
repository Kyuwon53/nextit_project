package com.mayday.reply.service;

import java.util.List;

import com.mayday.reply.vo.ReplySearchVO;
import com.mayday.reply.vo.ReplyVO;

public interface IReplyService {
	public List<ReplyVO> getReplyList(ReplySearchVO searchVO);
	public List<ReplyVO> getUserReplyList(ReplySearchVO searchVO);
	
	public void registReply(ReplyVO reply);
	
	public void modifyReply(ReplyVO reply);
	
	public void removeReply(ReplyVO reply);
}
