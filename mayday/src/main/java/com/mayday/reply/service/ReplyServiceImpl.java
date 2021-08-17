package com.mayday.reply.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mayday.reply.dao.IReplyDao;
import com.mayday.reply.vo.ReplySearchVO;
import com.mayday.reply.vo.ReplyVO;
import com.mayday.replylike.dao.IReplyLikeDao;

@Service
public class ReplyServiceImpl implements IReplyService{
	@Inject
	IReplyDao replyDao;
	@Inject
	IReplyLikeDao replyLikeDao;
	
	@Override
	public List<ReplyVO> getReplyList(ReplySearchVO searchVO) {
		int totalCaount=replyDao.getReplyCount(searchVO);
		searchVO.setTotalRowCount(totalCaount);
		searchVO.pageSetting();		
		return replyDao.getReplyList(searchVO);
	}
	
	@Override
	public List<ReplyVO> getUserReplyList(ReplySearchVO searchVO) {
		int totalCaount=replyDao.getReplyCount(searchVO);
		searchVO.setTotalRowCount(totalCaount);
		searchVO.pageSetting();
		return replyDao.getUserReplyList(searchVO);
	}

	@Override
	public void registReply(ReplyVO reply) {
		replyDao.insertReply(reply);
	}

	@Override
	public void modifyReply(ReplyVO reply) {
		//replyDao.getReply(reply.getReNo()); 오류 잡을 때 사용 할라고 만듬
		replyDao.updateReply(reply);
	}

	@Override
	public void removeReply(ReplyVO reply) {
		replyDao.deleteReply(reply);
		
	}

}
