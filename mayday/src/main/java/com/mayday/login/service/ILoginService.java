package com.mayday.login.service;


import com.mayday.exception.BizNotEffectedException;
import com.mayday.exception.BizNotFoundException;
import com.mayday.exception.BizPasswordNotMatchedException;
import com.mayday.login.vo.UserVO;
import com.mayday.member.vo.MemberVO;

public interface ILoginService {
	public UserVO login(String userId, String userPass) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException;
	
	public String whereMyId(String memMail) throws BizNotFoundException;
	public String whereMyPass(MemberVO member) throws BizNotFoundException;
	public void updateMemState(MemberVO member) throws BizNotFoundException, BizNotEffectedException;
}
