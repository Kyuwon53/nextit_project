package com.mayday.common.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mayday.common.vo.MemberProfileVO;
import com.mayday.exception.BizNotEffectedException;
import com.mayday.exception.BizNotFoundException;

@Service
public class MemberProfileServiceImpl implements IMemberProfileService {
	
	@Inject
	IMemberProfileDao memberProfileDao;
	
	@Override
	public MemberProfileVO getMemProfile(String memId) throws BizNotFoundException {
		MemberProfileVO memberProfile = memberProfileDao.getMemProfile(memId);
		if(memberProfile == null) throw new BizNotFoundException();
		return memberProfile;
	}

	@Override
	public void removeMemProfile(MemberProfileVO memberProfileVO) throws BizNotFoundException, BizNotEffectedException {
		MemberProfileVO vo = memberProfileDao.getMemProfile(memberProfileVO.getMemId());
		if(vo == null) throw new BizNotFoundException();
		int cnt = memberProfileDao.deleteMemProfile(memberProfileVO.getMemId());
		if(cnt < 1) throw new BizNotEffectedException();
	}

	@Override
	public void registMemProfile(MemberProfileVO memberProfileVO) throws BizNotEffectedException {
		MemberProfileVO vo = memberProfileDao.getMemProfile(memberProfileVO.getMemId());
		if(vo == null) {
			int cnt = memberProfileDao.insertMemProfile(memberProfileVO);
			if(cnt < 1) throw new BizNotEffectedException();
		}else {
			int cnt = memberProfileDao.updateMemProfile(memberProfileVO);
			if(cnt < 1) throw new BizNotEffectedException();
		}
	}
}
