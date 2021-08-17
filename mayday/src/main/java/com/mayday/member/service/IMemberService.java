package com.mayday.member.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.mayday.exception.BizNotEffectedException;
import com.mayday.exception.BizNotFoundException;
import com.mayday.member.vo.MemberSearchVO;
import com.mayday.member.vo.MemberVO;

public interface IMemberService {
	// List
	public List<MemberVO> getMemberList(MemberSearchVO searchVO);
	public List<MemberVO> getExpertList(MemberSearchVO searchVO);
	// get
	public MemberVO getMember(String memId) throws BizNotFoundException;
	// modify
	public void Modify(MemberVO member) throws BizNotFoundException, BizNotEffectedException;
	public void ModifyInfo(MemberVO member) throws BizNotFoundException, BizNotEffectedException;
	//regist
	public void registMember(MemberVO member) throws  BizNotEffectedException;
	public void registLawyer(MemberVO member) throws  BizNotEffectedException;
	// id pw 찾기
	
	// 회원 탈퇴하기 
	public void passCheck(MemberVO member) throws BizNotFoundException;
	public void leaveMember(MemberVO member) throws BizNotFoundException, BizNotEffectedException;
	
	//비밀번호 변경
	public void changePassWord(MemberVO member) throws BizNotFoundException, BizNotEffectedException;
	// 엑셀 다운로드 
	public void excelDown(MemberSearchVO searchVO,String positCd ,String title ,HttpServletResponse resp) throws Exception;
}
