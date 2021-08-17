package com.mayday.law.service;

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

import com.mayday.petition.vo.PetitionVO;
@Service
public class LawServiceImpl implements ILawService{
	@Inject
	ILawDao lawDao;
	@Override
	public List<LawVO> getLawList(LawSearchVO searchVO) {
		int totalRowCount = lawDao.getCountLaw(searchVO);
		searchVO.setTotalRowCount(totalRowCount);
		searchVO.pageSetting();
		return lawDao.getLawList(searchVO);
	}
	@Override
	public int removeLaw(LawVO law) {
		return lawDao.deleteLaw(law);
	}
	@Override
	public void excelDown(HttpServletResponse resp) throws Exception {
		List<LawVO> lawList = lawDao.getLawListForExcel();
		//Excel Down 시작
		Workbook workbook = new HSSFWorkbook();
		
		// 시트 생성
		Sheet sheet = workbook.createSheet("법령 게시판_관리");
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
		String[] headerArray = {"NO", "법령종류","법령명","공포번호","공포일자","시행일자","재정.개정구분","소관부처","내용링크"};
		row = sheet.createRow(rowNo++);
		for(int i = 0; i< headerArray.length; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(headStyle);
			cell.setCellValue(headerArray[i]);
		}
		
		for(LawVO excelData: lawList) {
			row = sheet.createRow(rowNo++);
			cell = row.createCell(0);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getBoNo());
			
			cell = row.createCell(1);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getLawCate());
			
			cell = row.createCell(2);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getLawName());
			
			cell = row.createCell(3);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getLawNo());
			
			cell = row.createCell(4);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getLawDate());
			
			cell = row.createCell(5);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getLawStartDate());
			
			cell = row.createCell(6);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getLawClass());
			
			cell = row.createCell(7);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getLawDep());
			
			cell = row.createCell(8);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getLawLink());
		}
		
		// 컨텐츠 타입과 파일명 지정
		resp.setContentType("ms-vnd/excel");
		resp.setHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode("법령게시판_관리.xls","UTF8"));
		
		//엑셀 출력
		workbook.write(resp.getOutputStream());
		workbook.close();
	}
}
