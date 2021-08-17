package com.mayday.main;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mayday.board.service.IBoardService;
import com.mayday.board.vo.BoardSerchVO;
import com.mayday.board.vo.BoardVO;
import com.mayday.faq.service.IFaqService;
import com.mayday.faq.vo.FaqSearchVO;
import com.mayday.faq.vo.FaqVO;
import com.mayday.law.service.ILawService;
import com.mayday.law.service.LawSearchVO;
import com.mayday.law.service.LawVO;
import com.mayday.member.service.IMemberService;
import com.mayday.news.service.INewsService;
import com.mayday.news.service.NewsSearchVO;
import com.mayday.news.service.NewsVO;
import com.mayday.petition.service.IPetitionService;
import com.mayday.petition.vo.PetitionSearchVO;
import com.mayday.petition.vo.PetitionVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Inject
	IFaqService faqService;
	@Inject
	IPetitionService petitionService;
	@Inject
	INewsService newsService;
	@Inject
	ILawService lawService;
	@Inject
	IBoardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
//	private Logger log = LoggerFactory.getLogger(this.getClass());
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home( PetitionSearchVO petitionSearchVO,
							NewsSearchVO newsSearchVO,
							LawSearchVO lawSearchVO,
							Locale locale,
							FaqSearchVO precedentSearchVO,
							BoardSerchVO noticeSearchVO,
							FaqSearchVO counselSearchVO,
							BoardSerchVO freeSearchVO,
							BoardSerchVO questionSearchVO,
							FaqSearchVO faqSearchVO,
							Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		// 청원
		petitionSearchVO.setRowSizePerPage(5);
		List<PetitionVO> petitionList = petitionService.getPetitionList(petitionSearchVO);
		model.addAttribute("petitionList", petitionList);
		// 뉴스
		newsSearchVO.setRowSizePerPage(5);
		List<NewsVO> newsList=newsService.getNewsList(newsSearchVO);
		model.addAttribute("newsList", newsList);
		// 법령 5개씩 리스트
		lawSearchVO.setRowSizePerPage(5);
		List<LawVO> lawList = lawService.getLawList(lawSearchVO);
		model.addAttribute("lawList", lawList);
		// 판례 5개씩 리스트
		precedentSearchVO.setFaqParentCd("FAQ10 ");
		precedentSearchVO.setRowSizePerPage(5);
		List<FaqVO> precedentsList = faqService.getFaqList(precedentSearchVO);
		model.addAttribute("precedentsList", precedentsList);
		// 공지사항 5개씩 리스트
		noticeSearchVO.setBoardParentCd("BOD30 ");
		noticeSearchVO.setRowSizePerPage(5);
		List<BoardVO> noticeList = boardService.getBoardList(noticeSearchVO);
		model.addAttribute("noticeList", noticeList);
		logger.debug("noticeSearchVO={}",noticeSearchVO);
		// 상담사례
		counselSearchVO.setFaqParentCd("FAQ20 ");
		counselSearchVO.setRowSizePerPage(5);
		List<FaqVO> counselList = faqService.getFaqList(counselSearchVO);
		model.addAttribute("counselList", counselList);
		
		// 자유게시판
		freeSearchVO.setBoardParentCd("BOD20 ");
		freeSearchVO.setRowSizePerPage(5);
		List<BoardVO> freeList = boardService.getBoardList(freeSearchVO);
		model.addAttribute("freeList", freeList);
		logger.debug("freeSearchVO={}",freeSearchVO);
		// 질문게시판
		questionSearchVO.setBoardParentCd("BOD10 ");
		questionSearchVO.setRowSizePerPage(5);
		List<BoardVO> questionList = boardService.getBoardList(questionSearchVO);
		model.addAttribute("questionList", questionList);
		logger.debug("questionSearchVO={}",questionSearchVO);
		// 자주 묻는 질문
		faqSearchVO.setFaqParentCd("FAQ30 ");
		faqSearchVO.setRowSizePerPage(5);
		List<FaqVO> faqList = faqService.getFaqList(faqSearchVO);
		model.addAttribute("faqList", faqList);
		logger.debug("faqSearchVO={}",faqSearchVO);
		//date
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

}
