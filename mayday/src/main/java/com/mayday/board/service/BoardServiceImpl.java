package com.mayday.board.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mayday.board.vo.BoardSerchVO;
import com.mayday.board.vo.BoardVO;
import com.mayday.exception.BizNotEffectedException;
import com.mayday.exception.BizNotFoundException;


@Service
public class BoardServiceImpl implements IBoardService {

	@Inject
	private IBoardDao boardDao;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<BoardVO> getBoardList(BoardSerchVO searchVO) {
		int totalRowCount = boardDao.getCountBoard(searchVO);
		searchVO.setTotalRowCount(totalRowCount);
		searchVO.pageSetting();
		return boardDao.getboardList(searchVO);
	}
	
	@Override
	public List<BoardVO> getUserBoardList(BoardSerchVO searchVO) {
		int totalRowCount = boardDao.getCountBoard(searchVO);
		searchVO.setTotalRowCount(totalRowCount);
		searchVO.pageSetting();
		return boardDao.getUserBoardList(searchVO);
	}

	@Override
	public BoardVO getboard(int boNo) throws BizNotFoundException {
		BoardVO board = boardDao.getboard(boNo);
		if (board == null) {
			throw new BizNotFoundException();
		}
		return board;
	}

	@Override
	public void increaseboard(int boNo) throws BizNotEffectedException {
		int cnt = boardDao.increaseHit(boNo);
		if (cnt < 1)
			throw new BizNotEffectedException();
	}

	@Override
	public void modifyboard(BoardVO board) throws BizNotFoundException, BizNotEffectedException {
		int cnt = boardDao.updateBoard(board);
		if (cnt < 1)
			throw new BizNotEffectedException();
	}

	@Override
	public void removeboard(BoardVO board) throws BizNotFoundException, BizNotEffectedException {
		BoardVO vo = boardDao.getboard(board.getBoNo());
		if (vo == null)
			throw new BizNotFoundException();
		int cnt = boardDao.deleteBoard(board);
		if (cnt < 1)
			throw new BizNotEffectedException();
	}

	@Override
	public void registboard(BoardVO board) throws BizNotEffectedException {
		int cnt = boardDao.insertBoard(board);
		if (cnt < 1)
			throw new BizNotEffectedException();

	}

	@Override
	public void registReboard(BoardVO board) throws BizNotEffectedException {
		int cnt = boardDao.insertReBoard(board);
		if(cnt<1) throw new BizNotEffectedException();
	}

	@Override
	public void excelDown(BoardSerchVO searchVO,String title,HttpServletResponse resp) throws Exception {
		int totalRowCount = boardDao.getCountBoard(searchVO);
		searchVO.setLastRow(totalRowCount);
		List<BoardVO> boardList = boardDao.getboardList(searchVO);
		//Excel Down 시작
		Workbook workbook = new HSSFWorkbook();
		
		// 시트 생성
		Sheet sheet = workbook.createSheet(title+"게시판_관리");
		//행,열,열번호
		Row row = null;
		Cell cell = null;
		int rowNo = 0;
		
		// 테이블 헤더용 스타일
		CellStyle headStyle = workbook.createCellStyle();
		//가는 경계선을 가집니다. 
		headStyle.setBorderTop(BorderStyle.THIN);
		headStyle.setBorderBottom(BorderStyle.THIN);
		headStyle.setBorderLeft(BorderStyle.THIN);
		headStyle.setBorderRight(BorderStyle.THIN);
		
		CellStyle bodyStyle = workbook.createCellStyle();
		bodyStyle.setBorderTop(BorderStyle.THIN);
		bodyStyle.setBorderBottom(BorderStyle.THIN);
		bodyStyle.setBorderLeft(BorderStyle.THIN);
		bodyStyle.setBorderRight(BorderStyle.THIN);
		
		// 헤더명 설정 
		String[] headerArray = {"NO", "제목","내용","등록일","등록자","카테고리"};
		row = sheet.createRow(rowNo++);
		for(int i = 0; i< headerArray.length; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(headStyle);
			cell.setCellValue(headerArray[i]);
		}
		
		for(BoardVO excelData: boardList) {
			row = sheet.createRow(rowNo++);
			cell = row.createCell(0);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getBoNo());
			
			cell = row.createCell(1);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getBoTitle());
			
			cell = row.createCell(2);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getBoContent());
			
			cell = row.createCell(3);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getBoRegDate());
			
			cell = row.createCell(4);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getMemId());
			
			cell = row.createCell(5);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getCodeName());
		}
		
		// 컨텐츠 타입과 파일명 지정
		resp.setContentType("ms-vnd/excel");
		resp.setHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode(title+"게시판_관리.xls","UTF8"));
		
		//엑셀 출력
		workbook.write(resp.getOutputStream());
		workbook.close();
	}
}
