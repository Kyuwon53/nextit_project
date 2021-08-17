package com.mayday.replylike.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mayday.replylike.vo.ReplyLikeVO;
@Mapper
public interface IReplyLikeDao {
// ?? 뭐하는건지 이따가 써주세용  
public ReplyLikeVO getReplyLikeList(ReplyLikeVO replyLike);
// 유저가 좋아요 한 댓글 리스트
public List<ReplyLikeVO> getmemLikeNo(ReplyLikeVO replyLike);
public int insertReplyLike(ReplyLikeVO replyLike);
public int deleteReplyLike(ReplyLikeVO replyLike);

}
