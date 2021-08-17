package com.mayday.qna.service;

import java.util.List;

import com.mayday.exception.BizNotEffectedException;
import com.mayday.exception.BizNotFoundException;
import com.mayday.qna.vo.QnASearchVO;
import com.mayday.qna.vo.QnAVO;

public interface IQnABoardService {
	
	// 문의게시판 미답변 수 
	public int countQnABoard();
	// 답변 확인 
	public int countAns(String memId);
	// 관리자가 보는 1대1 문의 게시판
	public List<QnAVO> getList(QnASearchVO searchVO);
	// 의뢰인이 보는 본인 게시판
	public List<QnAVO> getQnAList(String memId);
	// 변호사가 보는 의뢰인 문의 게시판 
	public QnAVO getQnABoard(int boNo);
	// 1대1 문의게시판 등록, 수정, 삭제 
	public void registQnABoard(QnAVO qnaBoard)throws BizNotEffectedException;
	public void modifyQnABoard(QnAVO qnaBoard)throws BizNotFoundException, BizNotEffectedException;
	public void removeQnABoard(int boNo)throws BizNotFoundException, BizNotEffectedException;
	// 고객이 원글 삭제시 답글도 삭제 
	public void removeAnsBoard(int originalNo)throws BizNotEffectedException;
	
	// 변호사,노무사,관리자 답변 등록, 수정, 삭제
	public void registAnsBoard(QnAVO qnaBoard)throws BizNotEffectedException;
	
	// 문의, 답변 확인
	public void checkQna(QnAVO qnaBoard);
}
