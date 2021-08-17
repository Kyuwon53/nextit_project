package com.mayday.replylike.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mayday.replylike.dao.IReplyLikeDao;
import com.mayday.replylike.vo.ReplyLikeVO;

@Service
public class ReplyLikeServiceImpl implements IReplyLikeService{

	@Inject
	private IReplyLikeDao replyLikeDao;
	
	@Override
	public void replyLike(ReplyLikeVO replyLike) {
		ReplyLikeVO vo = replyLikeDao.getReplyLikeList(replyLike);
		if(vo != null) {
		replyLikeDao.deleteReplyLike(replyLike);
		}else {
		replyLikeDao.insertReplyLike(replyLike);
		}
	}
	@Override
	public List<ReplyLikeVO> getreplyLike(ReplyLikeVO replyLike) {
		return replyLikeDao.getmemLikeNo(replyLike);
	}

}
