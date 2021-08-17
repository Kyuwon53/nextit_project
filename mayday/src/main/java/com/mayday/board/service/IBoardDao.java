package com.mayday.board.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mayday.board.vo.BoardSerchVO;
import com.mayday.board.vo.BoardVO;

@Mapper
public interface IBoardDao {
	public int getCountBoard(BoardSerchVO searchVO); //페이징처리에 필요

	public BoardVO getboard(int boNo);
	
	public List<BoardVO> getboardList(BoardSerchVO searchVO);
	public List<BoardVO> getUserBoardList(BoardSerchVO searchVO);	// 사용자가 작성한 페이지 가져오기
	
	public int increaseHit(int boNo);
	public int updateBoard(BoardVO board);
	public int deleteBoard(BoardVO board);
	public int insertBoard(BoardVO board);
	
	public int insertReBoard(BoardVO board);
	
	
}
