package com.mayday.code.service;

import java.util.List;

import com.mayday.code.vo.CodeVO;

public interface ICodeService {
	public List<CodeVO> getCodeList(String codeParentCd);
	public CodeVO getCodeName(String codeCd);
}
