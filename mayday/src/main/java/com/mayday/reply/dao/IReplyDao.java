package com.mayday.reply.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mayday.reply.vo.ReplySearchVO;
import com.mayday.reply.vo.ReplyVO;

@Mapper
public interface IReplyDao {
	public int getReplyCount(ReplySearchVO searchVO);

	public List<ReplyVO> getReplyList(ReplySearchVO searchVO);
	public List<ReplyVO> getUserReplyList(ReplySearchVO searchVO);

	public ReplyVO getReply(int reNo);

	public int insertReply(ReplyVO reply);
	public int updateReply(ReplyVO reply);
	public int deleteReply(ReplyVO reply);
}
