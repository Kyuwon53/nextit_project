package com.mayday.faq.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.mayday.exception.BizNotEffectedException;
import com.mayday.exception.BizNotFoundException;
import com.mayday.faq.vo.FaqSearchVO;
import com.mayday.faq.vo.FaqVO;

@Service
public class FaqServiceImpl implements IFaqService {
	
	@Inject
	IFaqDao faqDao;
	
	@Override
	public List<FaqVO> getFaqList(FaqSearchVO searchVO){
		int totalRowCount = faqDao.getCountFaq(searchVO);
		searchVO.setTotalRowCount(totalRowCount);
		searchVO.pageSetting();
		return faqDao.getFaqList(searchVO);
	}

	@Override
	public FaqVO getFaq(int boNo) throws BizNotFoundException {
		FaqVO faq = faqDao.getFaq(boNo);
		if(faq == null) {
			throw new BizNotFoundException();
		}
		//faq.setBoContent(faq.getBoContent().replaceAll("\n", "<br>"));
		return faq;
	}
	
	@Override
	public void increaseFaq(int boNo) throws BizNotEffectedException {
		int cnt = faqDao.increaseHit(boNo);
		if(cnt < 1) throw new BizNotEffectedException();
	}

	@Override
	public void modifyFaq(FaqVO faq) throws BizNotFoundException, BizNotEffectedException {
		FaqVO vo = faqDao.getFaq(faq.getBoNo());
		if(vo == null) throw new BizNotFoundException();
		int cnt = faqDao.updateFaq(faq);
		if(cnt < 1 ) throw new BizNotEffectedException();
	}

	@Override
	public void removeFaq(FaqVO faq) throws BizNotFoundException, BizNotEffectedException {
		FaqVO vo = faqDao.getFaq(faq.getBoNo());
		if(vo == null) throw new BizNotFoundException();
		int cnt = faqDao.deleteFaq(faq);
		if(cnt < 1 ) throw new BizNotEffectedException();
	}

	@Override
	public void registFaq(FaqVO faq) throws BizNotEffectedException {
		int cnt = faqDao.insertFaq(faq);
		if(cnt < 1) throw new BizNotEffectedException();
	}

	@Override
	public void excelDown(FaqSearchVO searchVO, String title ,HttpServletResponse resp) throws Exception {
		int totalRowCount = faqDao.getCountFaq(searchVO);
		searchVO.setLastRow(totalRowCount);
		List<FaqVO> faqList = faqDao.getFaqList(searchVO);
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
		headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
		headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// 테이블 바디 스타일
		CellStyle bodyStyle = workbook.createCellStyle();
		bodyStyle.setBorderTop(BorderStyle.THIN);
		bodyStyle.setBorderBottom(BorderStyle.THIN);
		bodyStyle.setBorderLeft(BorderStyle.THIN);
		bodyStyle.setBorderRight(BorderStyle.THIN);
		
		// 헤더명 설정 
		String[] headerArray = {"NO", "제목","질문","답변","등록자","카테고리"};
		row = sheet.createRow(rowNo++);
		for(int i = 0; i< headerArray.length; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(headStyle);
			cell.setCellValue(headerArray[i]);
		}
		
		for(FaqVO excelData: faqList) {
			row = sheet.createRow(rowNo++);
			cell = row.createCell(0);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getBoNo());
			
			cell = row.createCell(1);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getBoTitle());
			
			cell = row.createCell(2);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getBoQue());
			
			cell = row.createCell(3);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getBoContent());
			
			cell = row.createCell(4);
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
