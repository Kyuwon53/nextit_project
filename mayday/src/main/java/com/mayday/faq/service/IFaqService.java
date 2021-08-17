package com.mayday.faq.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.mayday.exception.BizNotEffectedException;
import com.mayday.exception.BizNotFoundException;
import com.mayday.faq.vo.FaqSearchVO;
import com.mayday.faq.vo.FaqVO;

public interface IFaqService {
	
	public List<FaqVO> getFaqList(FaqSearchVO searchVO);
	public FaqVO getFaq(int boNo) throws BizNotFoundException;
	public void increaseFaq(int boNo) throws BizNotEffectedException;
	public void modifyFaq(FaqVO faq) throws BizNotFoundException,BizNotEffectedException;
	public void removeFaq(FaqVO faq) throws BizNotFoundException,BizNotEffectedException;
	public void registFaq(FaqVO faq) throws BizNotEffectedException;
	
	// 엑셀 다운로드 
	public void excelDown(FaqSearchVO searchVO,String title ,HttpServletResponse resp) throws Exception;
	
}
