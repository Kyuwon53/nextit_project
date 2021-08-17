package com.mayday.common.service;

import com.mayday.common.vo.MemberProfileVO;
import com.mayday.exception.BizNotEffectedException;
import com.mayday.exception.BizNotFoundException;

public interface IMemberProfileService {
	
	public MemberProfileVO getMemProfile(String memId) throws BizNotFoundException;
	public void removeMemProfile(MemberProfileVO memberProfileVO) throws BizNotFoundException,BizNotEffectedException;
	public void registMemProfile(MemberProfileVO memberProfileVO) throws BizNotEffectedException;
}
