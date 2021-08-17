package com.mayday.news.web;

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

import com.mayday.code.service.ICodeService;
import com.mayday.code.vo.CodeVO;
import com.mayday.common.vo.ResultMessageVO;
import com.mayday.news.service.INewsService;
import com.mayday.news.service.NewsSearchVO;
import com.mayday.news.service.NewsVO;
import com.mayday.petition.vo.PetitionSearchVO;

@Controller
@RequestMapping("/news")
public class NewsController {
	@Inject
	private INewsService newsService;
	@Inject
	private ICodeService codeService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@ModelAttribute("codeList")
	public List<CodeVO> cateList(){
		List<CodeVO> codeList=codeService.getCodeList("NEW00 ");
		return codeList;
	}
	@RequestMapping("/newsList")
	public String getList(@ModelAttribute("searchVO")NewsSearchVO searchVO,Model model) {
		logger.info("searchVO={}",searchVO);
		List<NewsVO> newsList=newsService.getNewsList(searchVO);
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("newsList", newsList);
	return"news/newsList";
	}
	@RequestMapping("/newsForm")
	public String newsForm(@ModelAttribute("news")NewsVO news,Model model) {
		logger.info("newsForm");
	return"news/newsForm";
	}
	@RequestMapping("/newsRegist")
	public String getForm(@ModelAttribute("news")NewsVO news,Model model) {
		logger.info("news={}",news);
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		newsService.createNews(news);
		resultMessageVO.setMessage("등록이 완료되었습니다.");
		resultMessageVO.setResult(true);
		resultMessageVO.setTitle("등록");
		resultMessageVO.setUrl("/news/newsList");
		resultMessageVO.setUrlTitle("뉴스게시판");
		model.addAttribute("resultMessage", resultMessageVO);
	return"common/message";
	}
	@RequestMapping("/newsRemove")
	public String newsRemove(@ModelAttribute("news")NewsVO news ,Model model) {
		logger.info("news={}",news);
		newsService.removeNews(news);
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		resultMessageVO.setMessage("삭제되었습니다.");
		resultMessageVO.setResult(true);
		resultMessageVO.setTitle("삭제");
		resultMessageVO.setUrl("/news/newsList");
		resultMessageVO.setUrlTitle("뉴스게시판");
		model.addAttribute("resultMessage", resultMessageVO);
	return"common/message";
	}
	@RequestMapping(value = "/excelDown")
	@ResponseBody
	public void excelDown(@ModelAttribute("searchVO") NewsSearchVO searchVO,HttpServletResponse resp, HttpServletRequest req) throws Exception{
		searchVO.setFirstRow(1);
		newsService.excelDown(searchVO,resp);
	}
}
