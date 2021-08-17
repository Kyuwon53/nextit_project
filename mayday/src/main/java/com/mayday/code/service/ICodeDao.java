package com.mayday.code.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mayday.code.vo.CodeVO;

@Mapper
public interface ICodeDao {
	public List<CodeVO> getCodeList(String codeParentCd);
	public CodeVO getCodeName(String codeCd);
}
