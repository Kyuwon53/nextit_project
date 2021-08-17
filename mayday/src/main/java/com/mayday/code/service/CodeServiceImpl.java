package com.mayday.code.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mayday.code.vo.CodeVO;

@Service
public class CodeServiceImpl implements ICodeService {
	@Inject
	ICodeDao codeDao;
		
	@Override
	public List<CodeVO> getCodeList(String codeParentCd) {
		return codeDao.getCodeList(codeParentCd);
	}

	@Override
	public CodeVO getCodeName(String codeCd) {
		return codeDao.getCodeName(codeCd);
	}
}
