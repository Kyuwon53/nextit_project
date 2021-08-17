package com.mayday.member.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mayday.member.vo.MemberSearchVO;
import com.mayday.member.vo.MemberVO;
@Mapper
public interface IMemberDao {
	public int getCountMember(MemberSearchVO searchVO);
	public int getCountExpert(MemberSearchVO searchVO);
	// user
	public	List<MemberVO> getMemberList(MemberSearchVO searchVO);
	public MemberVO getMember(String memId);
	public String getMemRoleByUserId(String memId);
	public int updateMember(MemberVO member);
	// expert
	public List<MemberVO> getExpertList(MemberSearchVO searchVO);
	public int insertMember(MemberVO member);
	public int insertLawyer(MemberVO member);
	public String checkCond(String memId);
	// mypage
	public int updateMemberInfo(MemberVO member);
	//id pw 찾기
	public String findMemberId(String memMail);
	public String findMemberPw(String memMail);
	// login 최근 접속 
	public int recentLogin(String userId);
	// login시 상태 변화 
	public int updateMemState(MemberVO member);
	
	// 회원 탈퇴
	public int leaveMember(MemberVO member);
	// 비밀번호 변경
	public int changePassWord(MemberVO member);
}
