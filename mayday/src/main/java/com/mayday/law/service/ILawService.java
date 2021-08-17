package com.mayday.law.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

public interface ILawService {
	public List<LawVO> getLawList(LawSearchVO searchVO);
	
	public void excelDown(HttpServletResponse resp) throws Exception;
	
	public int removeLaw(LawVO law);
}
