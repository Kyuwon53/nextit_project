package com.mayday.member.service;

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
import com.mayday.faq.vo.FaqVO;
import com.mayday.member.vo.MemberSearchVO;
import com.mayday.member.vo.MemberVO;
@Service
public class MemberServiceImpl implements IMemberService{
	@Inject
	private IMemberDao memberdao;
	
	@Override
	public List<MemberVO> getMemberList(MemberSearchVO searchVO) {

		int totalRowCount = memberdao.getCountMember(searchVO);
		System.out.println("카운트"+totalRowCount);
		searchVO.setTotalRowCount(totalRowCount);
		searchVO.pageSetting();
		return memberdao.getMemberList(searchVO);
	}
	
	@Override
	public List<MemberVO> getExpertList(MemberSearchVO searchVO) {
		int totalRowCount = memberdao.getCountExpert(searchVO);
		searchVO.setTotalRowCount(totalRowCount);
		searchVO.pageSetting();
		return memberdao.getExpertList(searchVO);
	}
	
	@Override
	public MemberVO getMember(String memId) throws BizNotFoundException {
		MemberVO member = memberdao.getMember(memId);
		if(member ==  null) throw new BizNotFoundException();
		return member;
	}
// modify
	// 관리자가 탈퇴여부,휴면여부 관리 
	@Override
	public void Modify(MemberVO member) throws BizNotFoundException, BizNotEffectedException {
		MemberVO vo = memberdao.getMember(member.getMemId());
		if(vo == null ) throw new BizNotFoundException();
		int cnt = 	memberdao.updateMember(member);
		if(cnt<1) throw new BizNotEffectedException();
				
	}
	// 마이페이지에서 개인정보 수정
	@Override
	public void ModifyInfo(MemberVO member) throws BizNotFoundException, BizNotEffectedException {
		MemberVO vo = memberdao.getMember(member.getMemId());
		if(vo == null) throw new BizNotFoundException();
		int cnt = memberdao.updateMemberInfo(member);
		if(cnt<1) throw new BizNotEffectedException();
	}
	
// regist
	@Override
	public void registMember(MemberVO member)throws BizNotEffectedException {
		MemberVO vo = memberdao.getMember(member.getMemId());
		int cnt = memberdao.insertMember(member);
		if(cnt<1) throw new BizNotEffectedException();
	}
	@Override
	public void registLawyer(MemberVO member)throws BizNotEffectedException {
//		MemberVO vo = memberdao.getMember(member.getMemId());
		int cnt = memberdao.insertLawyer(member);
		if(cnt<1) throw new BizNotEffectedException();
	}

	@Override
	public void leaveMember(MemberVO member) throws BizNotFoundException, BizNotEffectedException {
		MemberVO vo = memberdao.getMember(member.getMemId());
		if(vo == null || (!vo.getMemPass().equals(member.getMemPass()))) {
			System.out.println("not found exception");
			throw new BizNotFoundException();
		}
		int cnt = memberdao.leaveMember(member);
		if(cnt<1) throw new BizNotEffectedException();
	}

	@Override
	public void passCheck(MemberVO member) throws BizNotFoundException {
		MemberVO findMember = memberdao.getMember(member.getMemId());
		if(findMember == null) throw new BizNotFoundException();
		if(!findMember.getMemPass().equals(member.getMemPass())) throw new BizNotFoundException();
	}

	@Override
	public void changePassWord(MemberVO member) throws BizNotFoundException, BizNotEffectedException {
		MemberVO findMember = memberdao.getMember(member.getMemId());
		if(findMember == null) throw new BizNotFoundException();
		int cnt = memberdao.changePassWord(member);
	}

	@Override
	public void excelDown(MemberSearchVO searchVO,String positCd ,String title, HttpServletResponse resp) throws Exception {
		int totalRowCount = 0;
		List<MemberVO> memberList = null;
		if (positCd.equals("expert")) {
			totalRowCount = memberdao.getCountExpert(searchVO);
			searchVO.setLastRow(totalRowCount);
			memberList = memberdao.getExpertList(searchVO);
		} else if (positCd.equals("counsel")) {
			totalRowCount = memberdao.getCountMember(searchVO);
			searchVO.setLastRow(totalRowCount);
			memberList = memberdao.getMemberList(searchVO);
		} 
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
		String[] headerArray = {"아이디", "이름","생년월일","이메일","이력","자격","가입일자","최근 접속 날짜"};
		row = sheet.createRow(rowNo++);
		for(int i = 0; i< headerArray.length; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(headStyle);
			cell.setCellValue(headerArray[i]);
		}
		
		for(MemberVO excelData: memberList) {
			row = sheet.createRow(rowNo++);
			cell = row.createCell(0);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getMemId());
			
			cell = row.createCell(1);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getMemName());
			
			cell = row.createCell(2);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getMemBir());
			
			cell = row.createCell(3);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getMemMail());
			
			cell = row.createCell(4);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getMemCareer());
			
			cell = row.createCell(5);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getMemPosit());
			
			cell = row.createCell(6);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getMemRegDate());
						
			cell = row.createCell(7);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(excelData.getMemEndDate());
		}
		
		// 컨텐츠 타입과 파일명 지정
		resp.setContentType("ms-vnd/excel");
		resp.setHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode(title+"게시판_관리.xls","UTF8"));
		
		//엑셀 출력
		workbook.write(resp.getOutputStream());
		workbook.close();
	}
}
