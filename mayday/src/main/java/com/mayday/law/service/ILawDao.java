package com.mayday.law.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface ILawDao {
	public int getCountLaw(LawSearchVO searchVO);
	public List<LawVO> getLawList(LawSearchVO searchVO);
	public List<LawVO> getLawListForExcel();
	
	public int deleteLaw(LawVO law);

}
