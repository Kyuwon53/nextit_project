package com.mayday.replylike.service;

import java.util.List;

import com.mayday.replylike.vo.ReplyLikeVO;

public interface IReplyLikeService {
	
public void replyLike(ReplyLikeVO replyLike);
public List<ReplyLikeVO> getreplyLike(ReplyLikeVO replyLike);
}
