package com.mayday.news.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.format.CellFormatType;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressBase.CellPosition;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements INewsService{

	@Inject
	private INewsDao newsDao;
	
	@Override
	public List<NewsVO> getNewsList(NewsSearchVO searchVO) {
		int totalrowCount = newsDao.getNewsCount(searchVO);
		searchVO.setTotalRowCount(totalrowCount);
		searchVO.pageSetting();
		return newsDao.getNewsList(searchVO);
	}

	@Override
	public void createNews(NewsVO news) {	
		newsDao.insertNews(news);
	}

	@Override
	public void removeNews(NewsVO news) {
		newsDao.deleteNews(news);
	}

	@Override
	public void excelDown(NewsSearchVO searchVO, HttpServletResponse resp) throws Exception {
		int totalRowCount = newsDao.getNewsCount(searchVO);
		searchVO.setLastRow(totalRowCount);
		List<NewsVO> newsList = newsDao.getNewsList(searchVO);
		//Excel Down 시작
		Workbook workbook = new HSSFWorkbook();
		
		// 시트 생성
		Sheet sheet = workbook.createSheet("뉴스 게시판_관리");
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
		String[] headerArray = {"NO", "구분","제목","언론사","기사 입력 날짜","링크"};
		row = sheet.createRow(rowNo++);
		for(int i = 0; i< headerArray.length; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(headStyle);
			cell.setCellValue(headerArray[i]);
		}
		
		for(NewsVO excelData: newsList) {
			row = sheet.createRow(rowNo++);
			cell = row.createCell(0);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getBoNo());
			
			cell = row.createCell(1);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getBoCdName());
			
			cell = row.createCell(2);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getNewsTitle());
			
			cell = row.createCell(3);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getNewsPress());
			
			cell = row.createCell(4);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getNewsRegDate());
			
			cell = row.createCell(5);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getNewsLink());
			
		}
		
		// 컨텐츠 타입과 파일명 지정
		resp.setContentType("ms-vnd/excel");
		resp.setHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode("뉴스_관리.xls","UTF8"));
		
		//엑셀 출력
		workbook.write(resp.getOutputStream());
		workbook.close();		
	}
}
