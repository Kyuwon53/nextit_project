package com.mayday.login.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mayday.exception.BizNotEffectedException;
import com.mayday.exception.BizNotFoundException;
import com.mayday.exception.BizPasswordNotMatchedException;
import com.mayday.login.vo.UserVO;
import com.mayday.member.service.IMemberDao;
import com.mayday.member.vo.MemberVO;

@Service
public class LoginServiceImpl implements ILoginService {

	@Inject
	IMemberDao memberDao;

	@Override
	public UserVO login(String userId, String userPass) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {

		MemberVO member = memberDao.getMember(userId);
		UserVO user = new UserVO();
		if (member == null) {
			throw new BizNotFoundException();
		} else {
			if (member.getMemPass().equals(userPass)) {
				memberDao.recentLogin(userId);
				user.setUserId(userId);
				user.setUserPass(userPass);
				user.setUserName(member.getMemName());
				user.setUserRole(memberDao.getMemRoleByUserId(member.getMemId()));
				user.setUserCond(memberDao.checkCond(userId));
				String cond = user.getUserCond();
				if (cond.equals("N")) {
					user.setUserCond(cond);
					throw new BizNotFoundException();
				}
				if(cond.equals("H")) {
					user.setUserCond(cond);
					throw new BizNotEffectedException();
				}
				System.out.println(user);
				return user;
			} else {
				throw new BizPasswordNotMatchedException();
			}
		}
	}

	public String whereMyId(String memMail) throws BizNotFoundException {

		String id = memberDao.findMemberId(memMail);
		if (id == null) {
			throw new BizNotFoundException();
		}
		return id;

	}

	public String whereMyPass(MemberVO member) throws BizNotFoundException {
		
		MemberVO findMember = memberDao.getMember(member.getMemId());
		if(findMember == null) throw new BizNotFoundException();
		if( ! findMember.getMemMail().equals(member.getMemMail())) throw new BizNotFoundException();
		
		return findMember.getMemPass();
//		String pass = memberDao.findMemberPw(member);
//		if (pass == null) {
//			throw new BizNotFoundException();
//		}
//		return pass;
	}

	@Override
	public void updateMemState(MemberVO member) throws BizNotFoundException, BizNotEffectedException {
		MemberVO vo = memberDao.getMember(member.getMemId());
		if(vo == null) throw new BizNotFoundException();
		int cnt = memberDao.updateMemState(member);
		if(cnt < 1) throw new BizNotEffectedException();
	}
}
