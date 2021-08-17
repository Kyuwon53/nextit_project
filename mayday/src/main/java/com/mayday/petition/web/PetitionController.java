package com.mayday.petition.web;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mayday.common.vo.ResultMessageVO;
import com.mayday.exception.BizNotEffectedException;
import com.mayday.exception.BizNotFoundException;
import com.mayday.faq.vo.FaqVO;
import com.mayday.petition.service.IPetitionService;
import com.mayday.petition.vo.PetitionSearchVO;
import com.mayday.petition.vo.PetitionVO;

@Controller
@RequestMapping("/petition")
public class PetitionController {
	@Inject
	IPetitionService petitionService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/petitionList")
	public String petitionList(@ModelAttribute("searchVO") PetitionSearchVO searchVO, Model model) {
		logger.info("searchVO={}",searchVO);
		List<PetitionVO> petitionList = petitionService.getPetitionList(searchVO);
		model.addAttribute("petitionList", petitionList);
		return "petition/petitionList";
	}
	
	@GetMapping("/petitionView")
	public String petitionView(@RequestParam(value = "boNo", required = true) int boNo, Model model) {
		logger.info("boNo={}", boNo);
		try {
			PetitionVO petition = petitionService.getPetition(boNo);
			model.addAttribute("petition", petition);
		} catch (BizNotFoundException e) {
			logger.error("error={}",e);
			model.addAttribute("e", e);
		}
		return "petition/petitionView";
	}
	
	@RequestMapping("/petitionEdit")
	public String petitionEdit(@RequestParam(value = "boNo", required = true)int boNo, Model model) {
		logger.info("boNo={}", boNo);
		try {
			PetitionVO petition = petitionService.getPetition(boNo);
			model.addAttribute("petition", petition);
		} catch (BizNotFoundException e) {
			model.addAttribute("error ={}", e);
		}
		return "petition/petitionEdit";
	}
	@PostMapping("/petitionModify")
	public String petitionModify(@ModelAttribute("petition") PetitionVO petition, Model model ) {
		logger.info("petition={}", petition);
		ResultMessageVO resultMessage = new ResultMessageVO();
		try {
			petitionService.modifyPetition(petition);
			resultMessage.setResult(true);
			resultMessage.setMessage("수정되었습니다.");
			resultMessage.setTitle("수정");
			resultMessage.setUrl("/petition/petitionView?boNo="+petition.getBoNo());
			resultMessage.setUrlTitle("해당 뷰");
		} catch (BizNotFoundException enf) {
			logger.error("error ={}", enf);
			resultMessage.setResult(false);
			resultMessage.setMessage("실패: 해당 글을 찾을 수 없습니다.");
			resultMessage.setTitle("수정");
			resultMessage.setUrl("/petition/petitionList");
			resultMessage.setUrlTitle("목록으로");
		} catch (BizNotEffectedException ene) {
			resultMessage.setResult(false);
			resultMessage.setMessage("실패: 수정 실패하였습니다.");
			resultMessage.setTitle("수정");
			resultMessage.setUrl("/petition/petitionView?boNo="+petition.getBoNo());
			resultMessage.setUrlTitle("해당 뷰");
		}
		model.addAttribute("resultMessage", resultMessage);
		return "common/message";
	}
	@RequestMapping("/petitionDelete")
	public String petitionDelete(@ModelAttribute("petition") PetitionVO petition, Model model) {
		logger.info("petition={}", petition);
		ResultMessageVO resultMessage = new ResultMessageVO();
		try {
			petitionService.removePetition(petition);
			resultMessage.setResult(true);
			resultMessage.setMessage("삭제되었습니다.");
			resultMessage.setTitle("삭제");
			resultMessage.setUrl("/petition/petitionList");
			resultMessage.setUrlTitle("목록으로");
		} catch (BizNotFoundException enf) {
			logger.error("error ={}", enf);
			resultMessage.setResult(false);
			resultMessage.setMessage("실패: 해당 글을 찾을 수 없습니다.");
			resultMessage.setTitle("삭제");
			resultMessage.setUrl("/petition/petitionList");
			resultMessage.setUrlTitle("목록으로");
		} catch (BizNotEffectedException ene) {
			resultMessage.setResult(false);
			resultMessage.setMessage("실패: 삭제 실패하였습니다.");
			resultMessage.setTitle("삭제");
			resultMessage.setUrl("/petition/petitionView?boNo="+petition.getBoNo());
			resultMessage.setUrlTitle("해당 뷰");
		}
		model.addAttribute("resultMessage", resultMessage);
		return "common/message";
	}

	@RequestMapping("/petitionForm")
	public String petitionForm(@ModelAttribute("petition") PetitionVO petition) {
		return "petition/petitionForm";
	}

	@PostMapping("/petitionRegist")
	public String petitionRegist(@ModelAttribute("petition") PetitionVO petition, Model model) {
		logger.info("petition={}", petition);
		ResultMessageVO resultMessage = new ResultMessageVO();
		try {
			petitionService.registPetition(petition);
			resultMessage.setResult(true);
			resultMessage.setMessage("등록되었습니다.");
			resultMessage.setTitle("등록");
			resultMessage.setUrl("/petition/petitionList");
			resultMessage.setUrlTitle("목록으로");
		} catch (BizNotEffectedException ene) {
			resultMessage.setResult(false);
			resultMessage.setMessage("실패: 등록 실패하였습니다.");
			resultMessage.setTitle("등록");
			resultMessage.setUrl("/petition/petitionList");
			resultMessage.setUrlTitle("목록으로");
		}
		model.addAttribute("resultMessage", resultMessage);
		return "common/message";
	}
	@RequestMapping(value = "/excelDown")
	@ResponseBody
	public void excelDown(@ModelAttribute("searchVO") PetitionSearchVO searchVO,HttpServletResponse resp, HttpServletRequest req) throws Exception{
		searchVO.setFirstRow(1);
		petitionService.excelDown(searchVO,resp);
	}
}
