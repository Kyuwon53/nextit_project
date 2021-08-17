package com.mayday.board.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.mayday.board.vo.BoardVO;
import com.mayday.board.vo.BoardSerchVO;
import com.mayday.exception.BizNotEffectedException;
import com.mayday.exception.BizNotFoundException;

public interface IBoardService {
	public void increaseboard(int boNo) throws BizNotEffectedException;

	public List<BoardVO> getBoardList(BoardSerchVO searchVO);
	public List<BoardVO> getUserBoardList(BoardSerchVO searchVO);
	
	public BoardVO getboard(int boNo) throws BizNotFoundException;
	
	public void modifyboard(BoardVO board) throws BizNotFoundException,BizNotEffectedException;
	public void removeboard(BoardVO board) throws BizNotFoundException,BizNotEffectedException;
	public void registboard(BoardVO board) throws BizNotEffectedException;
	
	public void registReboard(BoardVO board) throws BizNotEffectedException;
	
	// 엑셀 다운로드 
	public void excelDown(BoardSerchVO searchVO,String title,HttpServletResponse resp) throws Exception;
}
