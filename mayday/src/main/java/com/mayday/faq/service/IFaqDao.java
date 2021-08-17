package com.mayday.faq.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mayday.faq.vo.FaqSearchVO;
import com.mayday.faq.vo.FaqVO;

@Mapper
public interface IFaqDao {
	public int getCountFaq(FaqSearchVO searchVO);
	
	public List<FaqVO> getFaqList(FaqSearchVO searchVO);
	
	public FaqVO getFaq(int boNo);
	
	public int increaseHit(int boNo);
	public int updateFaq(FaqVO faq);
	public int deleteFaq(FaqVO faq);
	public int insertFaq(FaqVO faq);
}
