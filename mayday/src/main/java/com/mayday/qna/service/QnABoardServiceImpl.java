package com.mayday.qna.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mayday.exception.BizNotEffectedException;
import com.mayday.exception.BizNotFoundException;
import com.mayday.qna.vo.QnASearchVO;
import com.mayday.qna.vo.QnAVO;

@Service
public class QnABoardServiceImpl implements IQnABoardService {

	@Inject
	IQnADao qnaDao;
	
	@Override
	public int countQnABoard() {
		return qnaDao.countQnABoard();
	}

	@Override
	public int countAns(String memId) {
		return qnaDao.countAns(memId);
	}
	@Override
	public List<QnAVO> getList(QnASearchVO searchVO) {
		int totalRowCount = qnaDao.countQnA(searchVO);
		searchVO.setTotalRowCount(totalRowCount);
		searchVO.pageSetting();
		return qnaDao.getList(searchVO);
	}
	@Override
	public List<QnAVO> getQnAList(String memId) {
		return qnaDao.getQnAList(memId);
	}

	@Override
	public QnAVO getQnABoard(int boNo){
		return qnaDao.getQnABoard(boNo);
	}
	@Override
	public void registQnABoard(QnAVO qnaBoard) throws BizNotEffectedException {
		int cnt = qnaDao.insertQnABoard(qnaBoard);
		if( cnt < 1) throw new BizNotEffectedException();
	}

	@Override
	public void modifyQnABoard(QnAVO qnaBoard)throws BizNotFoundException, BizNotEffectedException {
		QnAVO vo = qnaDao.getQnABoard(qnaBoard.getBoNo());
		if(vo == null) throw new BizNotFoundException();
		int cnt = qnaDao.updateQnABoard(qnaBoard);
		if(cnt <1) throw new BizNotEffectedException();
	}

	@Override
	public void removeQnABoard(int boNo)throws BizNotFoundException, BizNotEffectedException {
		QnAVO vo = qnaDao.getQnABoard(boNo);
		if(vo == null) throw new BizNotFoundException();
		int cnt = qnaDao.deleteQnABoard(boNo);
		if(cnt < 1) throw new BizNotEffectedException();
	}
	@Override
	public void removeAnsBoard(int originalNo)throws BizNotEffectedException {
		int cnt = qnaDao.deleteAnsBoard(originalNo);
		if(cnt < 1) throw new BizNotEffectedException();
	}
	@Override
	public void registAnsBoard(QnAVO qnaBoard)throws BizNotEffectedException {
		int cnt = qnaDao.insertAnsBoard(qnaBoard);
		if(cnt < 1) throw new BizNotEffectedException();
	}

	@Override
	public void checkQna(QnAVO qnaBoard) {
		qnaDao.checkQna(qnaBoard);
	}
}
