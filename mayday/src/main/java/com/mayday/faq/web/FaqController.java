package com.mayday.faq.web;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.groups.Default;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mayday.code.service.ICodeService;
import com.mayday.code.vo.CodeVO;
import com.mayday.common.valid.Modify;
import com.mayday.common.valid.Regist;
import com.mayday.common.vo.ResultMessageVO;
import com.mayday.exception.BizNotEffectedException;
import com.mayday.exception.BizNotFoundException;
import com.mayday.faq.service.IFaqService;
import com.mayday.faq.vo.FaqSearchVO;
import com.mayday.faq.vo.FaqVO;

@Controller
@RequestMapping("/faq")
public class FaqController {
	@Inject
	IFaqService faqService;
	@Inject
	ICodeService codeService;

	@ModelAttribute("codeList")
	public List<CodeVO> codeList() {
		List<CodeVO> codeList = codeService.getCodeList("FAQ10 ");
		return codeList;
	}

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/{parentCd}/faqList")
	public String faqList(@ModelAttribute("searchVO") FaqSearchVO searchVO, Model model,
			@PathVariable("parentCd") String parentCd) {
		logger.info("searchVO={}", searchVO);
		CodeVO codeName = null;
		if (parentCd.equals("precedents")) {
			codeName = codeService.getCodeName("FAQ10 ");
			searchVO.setFaqParentCd("FAQ10 ");
		} else if (parentCd.equals("counsel")) {
			codeName = codeService.getCodeName("FAQ20 ");
			searchVO.setFaqParentCd("FAQ20 ");
		} else if (parentCd.equals("question")) {
			codeName = codeService.getCodeName("FAQ30 ");
			searchVO.setFaqParentCd("FAQ30 ");
		}
		model.addAttribute("codeName", codeName);
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("parentCd", parentCd);
		List<FaqVO> faqList = faqService.getFaqList(searchVO);
		model.addAttribute("faqList", faqList);
		return "faq/faqList";
	}

	@GetMapping("/faqView")
	public String faqView(@RequestParam(value = "boNo", required = true) int boNo, Model model) {
		logger.info("boNo={}", boNo);
		try {
			faqService.increaseFaq(boNo);
			FaqVO faq = faqService.getFaq(boNo);
			model.addAttribute("faq", faq);
		} catch (BizNotEffectedException | BizNotFoundException e) {
			logger.error("error ={}", e);
			model.addAttribute("e", e);
		}
		return "faq/faqView";
	}

	@RequestMapping("/faqEdit")
	public String faqEdit(@RequestParam(value = "boNo", required = true) int boNo, Model model) {
		logger.info("boNo={}", boNo);
		try {
			FaqVO faq = faqService.getFaq(boNo);
			model.addAttribute("faq", faq);
		} catch (BizNotFoundException e) {
			logger.error("error ={}", e);
			model.addAttribute("error ={}", e);
		}
		return "faq/faqEdit";
	}

	@PostMapping("/faqModify")
	public String faqModify(@ModelAttribute("faq") @Validated({ Default.class, Modify.class }) FaqVO faq,
			BindingResult errors, Model model) {
		logger.info("faq={}", faq);
		String parentCd = "";
		if (errors.hasErrors()) {
			if (faq.getCodeParentCd().contains("FAQ10")) {
				parentCd = "precedents";
			} else if (faq.getCodeParentCd().contains("FAQ20")) {
				parentCd = "counsel";
			} else if (faq.getCodeParentCd().contains("FAQ30")) {
				parentCd = "question";
			}
			List<CodeVO> codes = codeService.getCodeList(faq.getCodeParentCd());
			CodeVO parentCodeName = codeService.getCodeName(faq.getCodeParentCd());
			
			model.addAttribute("parentCd", parentCd);
			model.addAttribute("parentCodeName", parentCodeName);
			model.addAttribute("codes", codes);
			return "faq/faqEdit";
		}
		ResultMessageVO resultMessage = new ResultMessageVO();
		try {
			faqService.modifyFaq(faq);
			resultMessage.setResult(true);
			resultMessage.setMessage("수정되었습니다.");
			resultMessage.setTitle("수정");
			resultMessage.setUrl("/faq/faqView?boNo=" + faq.getBoNo());
			resultMessage.setUrlTitle("해당 뷰");
			resultMessage.setCode("200");
		} catch (BizNotFoundException enf) {
			logger.error("error ={}", enf);
			resultMessage.setResult(false);
			resultMessage.setMessage("실패: 해당 글을 찾을 수 없습니다.");
			resultMessage.setTitle("수정");
			if (faq.getCodeParentCd().contains("FAQ10")) {
				resultMessage.setUrl("/faq/precedents/faqList");
			} else if (faq.getCodeParentCd().contains("FAQ20")) {
				resultMessage.setUrl("/faq/counsel/faqList");
			} else if (faq.getCodeParentCd().contains("FAQ30")) {
				resultMessage.setUrl("/faq/question/faqList");
			}
			resultMessage.setCode("404");
			resultMessage.setUrlTitle("목록으로");
		} catch (BizNotEffectedException ene) {
			resultMessage.setResult(false);
			resultMessage.setMessage("실패: 수정 실패하였습니다.");
			resultMessage.setTitle("수정");
			resultMessage.setUrl("/faq/faqView?boNo=" + faq.getBoNo());
			resultMessage.setUrlTitle("해당 뷰");
			resultMessage.setCode("400");
		}
		model.addAttribute("resultMessage", resultMessage);
		return "common/message";
	}

	@RequestMapping("/faqDelete")
	public String faqDelete(@ModelAttribute("faq") FaqVO faq, Model model) {
		logger.info("faq={}", faq);
		ResultMessageVO resultMessage = new ResultMessageVO();
		try {
			faqService.removeFaq(faq);
			resultMessage.setResult(true);
			resultMessage.setMessage("삭제되었습니다.");
			resultMessage.setTitle("삭제");

			if (faq.getCodeParentCd().contains("FAQ10")) {
				resultMessage.setUrl("/faq/precedents/faqList");
			} else if (faq.getCodeParentCd().contains("FAQ20")) {
				resultMessage.setUrl("/faq/counsel/faqList");
			} else if (faq.getCodeParentCd().contains("FAQ30")) {
				resultMessage.setUrl("/faq/question/faqList");
			}
			resultMessage.setUrlTitle("목록으로");
		} catch (BizNotFoundException enf) {
			logger.error("error ={}", enf);
			resultMessage.setResult(false);
			resultMessage.setMessage("실패: 해당 글을 찾을 수 없습니다.");
			resultMessage.setTitle("삭제");
			if (faq.getCodeParentCd().contains("FAQ10")) {
				resultMessage.setUrl("/faq/precedents/faqList");
			} else if (faq.getCodeParentCd().contains("FAQ20")) {
				resultMessage.setUrl("/faq/counsel/faqList");
			} else if (faq.getCodeParentCd().contains("FAQ30")) {
				resultMessage.setUrl("/faq/question/faqList");
			}
			resultMessage.setUrlTitle("목록으로");
		} catch (BizNotEffectedException ene) {
			resultMessage.setResult(false);
			resultMessage.setMessage("실패: 삭제 실패하였습니다.");
			resultMessage.setTitle("삭제");
			resultMessage.setUrl("/faq/faqView?boNo=" + faq.getBoNo());
			resultMessage.setUrlTitle("해당 뷰");
		}
		model.addAttribute("resultMessage", resultMessage);
		return "common/message";
	}

	@RequestMapping("/{parentCd}/faqForm")
	public String faqForm(@ModelAttribute("faq") FaqVO faq, @PathVariable("parentCd") String parentCd, Model model) {
		if (parentCd.equals("precedents")) {
			faq.setCodeParentCd("FAQ10 ");
		} else if (parentCd.equals("counsel")) {
			faq.setCodeParentCd("FAQ20 ");
		} else if (parentCd.equals("question")) {
			faq.setCodeParentCd("FAQ30 ");
		}
		CodeVO parentCodeName = codeService.getCodeName(faq.getCodeParentCd());
		List<CodeVO> codes = codeService.getCodeList(faq.getCodeParentCd());
		model.addAttribute("parentCd", parentCd);
		model.addAttribute("parentCodeName", parentCodeName);
		model.addAttribute("codes", codes);
		return "faq/faqForm";
	}

	@PostMapping("/faqRegist")
	public String faqRegist(@ModelAttribute("faq") @Validated({ Default.class, Regist.class }) FaqVO faq,
			BindingResult errors, Model model) {
		logger.info("faq={}", faq);
		String parentCd = "";
		if (errors.hasErrors()) {
			if (faq.getCodeParentCd().contains("FAQ10")) {
				parentCd = "precedents";
			} else if (faq.getCodeParentCd().contains("FAQ20")) {
				parentCd = "counsel";
			} else if (faq.getCodeParentCd().contains("FAQ30")) {
				parentCd = "question";
			}
			List<CodeVO> codes = codeService.getCodeList(faq.getCodeParentCd());
			CodeVO parentCodeName = codeService.getCodeName(faq.getCodeParentCd());
			
			model.addAttribute("parentCd", parentCd);
			model.addAttribute("parentCodeName", parentCodeName);
			model.addAttribute("codes", codes);
			return "faq/faqForm";
		}
		ResultMessageVO resultMessage = new ResultMessageVO();

		try {
			faqService.registFaq(faq);
			resultMessage.setResult(true);
			resultMessage.setMessage("등록되었습니다.");
			resultMessage.setTitle("등록");
			if (faq.getCodeParentCd().contains("FAQ10")) {
				resultMessage.setUrl("/faq/precedents/faqList");
			} else if (faq.getCodeParentCd().contains("FAQ20")) {
				resultMessage.setUrl("/faq/counsel/faqList");
			} else if (faq.getCodeParentCd().contains("FAQ30")) {
				resultMessage.setUrl("/faq/question/faqList");
			}
			resultMessage.setUrlTitle("목록으로");
		} catch (BizNotEffectedException ene) {
			resultMessage.setResult(false);
			resultMessage.setMessage("실패: 등록 실패하였습니다.");
			resultMessage.setTitle("등록");
			if (faq.getCodeParentCd().contains("FAQ10")) {
				resultMessage.setUrl("/faq/precedents/faqList");
			} else if (faq.getCodeParentCd().contains("FAQ20")) {
				resultMessage.setUrl("/faq/counsel/faqList");
			} else if (faq.getCodeParentCd().contains("FAQ30")) {
				resultMessage.setUrl("/faq/question/faqList");
			}
			resultMessage.setUrlTitle("목록으로");
		}
		model.addAttribute("resultMessage", resultMessage);
		return "common/message";
	}

	@RequestMapping(value = "/{parentCd}/excelDown")
	@ResponseBody
	public void excelDown(@ModelAttribute("searchVO") FaqSearchVO searchVO, @PathVariable("parentCd") String parentCd,
			HttpServletResponse resp, HttpServletRequest req) throws Exception {
		String title = "";
		if (parentCd.equals("precedents")) {
			searchVO.setFaqParentCd("FAQ10 ");
			title = "판례";
		} else if (parentCd.equals("counsel")) {
			searchVO.setFaqParentCd("FAQ20 ");
			title = "상담사례";
		} else if (parentCd.equals("question")) {
			searchVO.setFaqParentCd("FAQ30 ");
			title = "자주 묻는 질문";
		}
		faqService.excelDown(searchVO, title, resp);
	}
}
