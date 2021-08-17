package com.mayday.law.web;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mayday.common.vo.ResultMessageVO;
import com.mayday.law.service.ILawService;
import com.mayday.law.service.LawSearchVO;
import com.mayday.law.service.LawVO;
import com.mayday.news.service.NewsVO;

@Controller
public class LawController {
	@Inject
	ILawService lawService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@RequestMapping("/law/lawList")
	public String lawList(@ModelAttribute("searchVO")LawSearchVO searchVO,Model model) {
		logger.info("lawList 부분");
		logger.info("searchVO={}",searchVO);
		List<LawVO> lawList = lawService.getLawList(searchVO);
		model.addAttribute("searchVO",searchVO);
		model.addAttribute("lawList", lawList);
		return "law/lawList";
	}
	
	@RequestMapping("/law/lawFormRemove")
	public String newsRemove(@ModelAttribute("law")LawVO law ,Model model) {
		logger.info("lawFormRemove");
		logger.info("law={}",law);
		lawService.removeLaw(law);
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		resultMessageVO.setMessage("삭제되었습니다.");
		resultMessageVO.setResult(true);
		resultMessageVO.setTitle("삭제");
		resultMessageVO.setUrl("/law/lawList");
		resultMessageVO.setUrlTitle("뉴스게시판");
		model.addAttribute("resultMessage", resultMessageVO);
	return"common/message";
	}
	@RequestMapping(value = "/law/excelDown")
	@ResponseBody
	public void excelDown(HttpServletResponse resp, HttpServletRequest req) throws Exception{
		lawService.excelDown(resp);
	}
}
