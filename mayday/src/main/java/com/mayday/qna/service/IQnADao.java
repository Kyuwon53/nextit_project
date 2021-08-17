package com.mayday.qna.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mayday.qna.vo.QnASearchVO;
import com.mayday.qna.vo.QnAVO;
@Mapper
public interface IQnADao {
	// 문의게시판 총 수 
	public int countQnA(QnASearchVO searchVO);
	// 문의게시판 미답변 수 
	public int countQnABoard();
	// 답변 갯수 
	public int countAns(String memId);
	// 관리자 용 
	public List<QnAVO> getList(QnASearchVO searchVO);
	// 본인 질문 리스트 
	public List<QnAVO> getQnAList(String memId);
	public QnAVO getQnABoard(int boNo);
	// 고객이 1대1 문의게시판 등록, 수정, 삭제 
	public int insertQnABoard(QnAVO qnaBoard);
	public int updateQnABoard(QnAVO qnaBoard);
	public int deleteQnABoard(int boNo);
	// 원글 삭제 시 답글도 삭제 
	public int deleteAnsBoard(int originalNo);
	// 변호사,노무사,관리자 답변 등록, 수정, 삭제
	public int insertAnsBoard(QnAVO qnaBoard);
	
	// 답변 등록
	public int checkQna(QnAVO qnaBoard);
}
