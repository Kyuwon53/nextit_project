package com.mayday.petition.service;

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
import org.springframework.stereotype.Service;

import com.mayday.exception.BizNotEffectedException;
import com.mayday.exception.BizNotFoundException;
import com.mayday.petition.vo.PetitionSearchVO;
import com.mayday.petition.vo.PetitionVO;

@Service
public class PetitionServiceImpl implements IPetitionService{
	@Inject
	IPetitionDao petitionDao;
	@Override
	public List<PetitionVO> getPetitionList(PetitionSearchVO searchVO) {
		int totalRowCount = petitionDao.getCountPetition(searchVO);
		searchVO.setTotalRowCount(totalRowCount);
		searchVO.pageSetting();
		return petitionDao.getPetitionList(searchVO);
	}

	@Override
	public PetitionVO getPetition(int boNo) throws BizNotFoundException {
		PetitionVO petition = petitionDao.getPetition(boNo);
		if(petition==null) {
			throw new BizNotFoundException();
		}
		return petition;
	}

	@Override
	public void modifyPetition(PetitionVO petition) throws BizNotFoundException, BizNotEffectedException {
		PetitionVO vo = petitionDao.getPetition(petition.getBoNo());
		if( vo == null ) throw new BizNotFoundException();
		int cnt = petitionDao.updatePetition(petition);
		if( cnt < 1) throw new BizNotEffectedException();
	}

	@Override
	public void removePetition(PetitionVO petition) throws BizNotFoundException, BizNotEffectedException {
		PetitionVO vo = petitionDao.getPetition(petition.getBoNo());
		if(vo == null) throw new BizNotFoundException();
		int cnt = petitionDao.deletePetition(petition);
		if( cnt < 1) throw new BizNotEffectedException();
	}

	@Override
	public void registPetition(PetitionVO petition) throws BizNotEffectedException {
		int cnt = petitionDao.insertPetition(petition);
		if(cnt <1) throw new BizNotEffectedException();
	}

	@Override
	public void excelDown(PetitionSearchVO searchVO,HttpServletResponse resp) throws Exception {
		int totalRowCount = petitionDao.getCountPetition(searchVO);
		searchVO.setLastRow(totalRowCount);
		List<PetitionVO> petitionList = petitionDao.getPetitionList(searchVO);
		//Excel Down 시작
		Workbook workbook = new HSSFWorkbook();
		
		// 시트 생성
		Sheet sheet = workbook.createSheet("청원 게시판_관리");
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
		String[] headerArray = {"NO", "제목","내용","링크","시작날짜","마감날짜","청원상태"};
		row = sheet.createRow(rowNo++);
		for(int i = 0; i< headerArray.length; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(headStyle);
			cell.setCellValue(headerArray[i]);
		}
		
		for(PetitionVO excelData: petitionList) {
			row = sheet.createRow(rowNo++);
			cell = row.createCell(0);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getBoNo());
			
			cell = row.createCell(1);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getPetTitle());
			
			cell = row.createCell(2);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getPetContent());
			
			cell = row.createCell(3);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getPetLink());
			
			cell = row.createCell(4);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getPetStartDate());
			
			cell = row.createCell(5);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getPetEndDate());
			
			cell = row.createCell(6);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getPetProgress());
		}
		
		// 컨텐츠 타입과 파일명 지정
		resp.setContentType("ms-vnd/excel");
		resp.setHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode("청원게시판_관리.xls","UTF8"));
		
		//엑셀 출력
		workbook.write(resp.getOutputStream());
		workbook.close();
	}

}
