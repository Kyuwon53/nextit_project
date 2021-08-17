package com.mayday.board.web;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mayday.board.service.IBoardService;
import com.mayday.board.vo.BoardSerchVO;
import com.mayday.board.vo.BoardVO;
import com.mayday.code.service.ICodeService;
import com.mayday.code.vo.CodeVO;
import com.mayday.common.vo.ResultMessageVO;
import com.mayday.exception.BizNotEffectedException;
import com.mayday.exception.BizNotFoundException;
import com.mayday.login.vo.UserVO;
import com.mayday.reply.service.IReplyService;
import com.mayday.reply.vo.ReplySearchVO;
import com.mayday.replylike.service.IReplyLikeService;
import com.mayday.replylike.vo.ReplyLikeVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Inject
	private IReplyService replyService;
	@Inject
	private IBoardService boardservice;
	@Inject
	private ICodeService codeservice;
	@Inject
	private IReplyLikeService replylikeservice;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@ModelAttribute("codeList")
	public List<CodeVO> codeList() {
		List<CodeVO> codeList = codeservice.getCodeList("BOD10 ");
		return codeList;

	}

	@RequestMapping("/{parentCd}/boardList")
	public String boardList(@ModelAttribute("searchVO") BoardSerchVO searchVO, Model model,
			@PathVariable("parentCd") String parentCd) {
		logger.info("searchVO={}", searchVO);
		CodeVO codeName = null;
		if (parentCd.equals("notice")) {
			codeName = codeservice.getCodeName("BOD30 ");
			searchVO.setBoardParentCd("BOD30 ");
		} else if (parentCd.equals("question")) {
			codeName = codeservice.getCodeName("BOD10 ");
			searchVO.setBoardParentCd("BOD10 ");
		} else if (parentCd.equals("free")) {
			codeName = codeservice.getCodeName("BOD20 ");
			searchVO.setBoardParentCd("BOD20 ");
		}
		model.addAttribute("searchVO", searchVO);
		List<BoardVO> boardList = boardservice.getBoardList(searchVO);
		model.addAttribute("boardList", boardList);
		model.addAttribute(boardList);
		model.addAttribute("codeName", codeName);
		return "board/boardList";
	}

	@RequestMapping("/boardView")
	public String boardView(@ModelAttribute("search") ReplySearchVO searchVO,
			@RequestParam(value = "boNo", required = true) int boNo, HttpServletRequest req, Model model) {
		logger.info("boNo={}", boNo);
		try {
			HttpSession session = req.getSession();
			UserVO user = (UserVO) session.getAttribute("USER_INFO");
			if (user != null) {
				ReplyLikeVO replylike = new ReplyLikeVO();
				replylike.setBoNo(boNo);
				replylike.setMemId(user.getUserId());
				replylikeservice.getreplyLike(replylike);
			}
			searchVO.setReParentNo(boNo);
			replyService.getReplyList(searchVO);
			boardservice.increaseboard(boNo);

			BoardVO board = boardservice.getboard(boNo);
			model.addAttribute("searchVO", searchVO);
			model.addAttribute("board", board);
		} catch (BizNotEffectedException | BizNotFoundException e) {
			logger.error("error ={}", e);
			model.addAttribute("e", e);
		}
		return "board/boardView";
	}

	@RequestMapping("/{parentCd}/boardForm")
	public String boardForm(@ModelAttribute("board") BoardVO board, @PathVariable("parentCd") String parentCd,
			Model model) {
		if (parentCd.equals("question")) {
			board.setCodeParentCd("BOD10 ");
		} else if (parentCd.equals("free")) {
			board.setCodeParentCd("BOD20 ");
		} else if (parentCd.equals("notice")) {
			board.setCodeParentCd("BOD30 ");
		}
		CodeVO parentCodeName = codeservice.getCodeName(board.getCodeParentCd());
		List<CodeVO> codes = codeservice.getCodeList(board.getCodeParentCd());
		model.addAttribute("parentCd", parentCd);
		model.addAttribute("parentCodeName", parentCodeName);
		model.addAttribute("codes", codes);
		return "board/boardForm";
	}

	@RequestMapping("/boardEdit")
	public String boardEdit(@ModelAttribute("boNo") int boNo, Model model) {
		logger.info("boNo={}", boNo);
		BoardVO board;
		try {
			board = boardservice.getboard(boNo);
			model.addAttribute("board", board);
			return "board/boardEdit";
		} catch (BizNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "board/boardEdit";
	}

	// 검사 받는 객체 = Command 객체(free)
	// 검사한 결과 = BindingResult는 Command객체 바로 뒤에
	// 검사 기준은 vo에 작성
	// 검사 통과 1개 못했을때 해당 필드만 재작성하면 됨
	@RequestMapping(value = "/boardModify", method = RequestMethod.POST)
	public String boardModify(@ModelAttribute("board") BoardVO board, Model model) {
		logger.info("boardModify");
		ResultMessageVO resultMessage = new ResultMessageVO();
		try {
			boardservice.modifyboard(board);
			resultMessage.setResult(true);
			resultMessage.setMessage("수정 성공");
			resultMessage.setTitle("수정");
			resultMessage.setUrl("/board/boardView?boNo=" + board.getBoNo());
			if (board.getCodeParentCd().contains("BOD10 ")) {
				resultMessage.setUrl("/board/question/boardList");
			} else if (board.getCodeParentCd().contains("BOD20 ")) {
				resultMessage.setUrl("/board/free/boardList");
			} else if (board.getCodeParentCd().contains("BOD30 ")) {
				resultMessage.setUrl("/board/notice/boardList");
			}
			resultMessage.setUrlTitle("목록으로");
		} catch (BizNotFoundException enf) {
			resultMessage.setResult(false);
			resultMessage.setMessage("수정 실패 notFound");
			resultMessage.setTitle("수정");
			if (board.getCodeParentCd().contains("BOD10 ")) {
				resultMessage.setUrl("/board/question/boardList");
			} else if (board.getCodeParentCd().contains("BOD20 ")) {
				resultMessage.setUrl("/board/free/boardList");
			} else if (board.getCodeParentCd().contains("BOD30 ")) {
				resultMessage.setUrl("/board/notice/boardList");
			}
			resultMessage.setUrlTitle("목록으로");
		} catch (BizNotEffectedException ene) {
			resultMessage.setResult(false);
			resultMessage.setMessage("수정 실패 notEffect");
			resultMessage.setTitle("수정");
			resultMessage.setUrl("/board/{parentCd}/boardList");
			resultMessage.setUrlTitle("목록으로");
		}
		model.addAttribute("resultMessage", resultMessage);
		return "common/message";
	}

	@RequestMapping("/boardDelete")
	public String boardDelete(@ModelAttribute("board") BoardVO board, Model model) {
		logger.info("board={}", board);
		ResultMessageVO resultMessage = new ResultMessageVO();
		try {
			boardservice.removeboard(board);
			resultMessage.setResult(true);
			resultMessage.setMessage("삭제되었습니다.");
			resultMessage.setTitle("삭제");
			if (board.getCodeParentCd().contains("BOD10")) {
				resultMessage.setUrl("/board/question/boardList");
			} else if (board.getCodeParentCd().contains("BOD20")) {
				resultMessage.setUrl("/board/free/boardList");
			} else if (board.getCodeParentCd().contains("BOD30")) {
				resultMessage.setUrl("/board/notice/boardList");
			}
			resultMessage.setUrlTitle("목록으로");
		} catch (BizNotFoundException enf) {
			logger.error("error ={}", enf);
			resultMessage.setResult(false);
			resultMessage.setMessage("실패: 해당 글을 찾을 수 없습니다.");
			resultMessage.setTitle("삭제");
			if (board.getCodeParentCd().contains("BOD10")) {
				resultMessage.setUrl("/board/question/boardList");
			} else if (board.getCodeParentCd().contains("BOD20")) {
				resultMessage.setUrl("/board/free/boardList");
			} else if (board.getCodeParentCd().contains("BOD30")) {
				resultMessage.setUrl("/board/notice/boardList");
			}
			resultMessage.setUrlTitle("목록으로");
		} catch (BizNotEffectedException ene) {
			resultMessage.setResult(false);
			resultMessage.setMessage("실패: 삭제 실패하였습니다.");
			resultMessage.setTitle("삭제");
			resultMessage.setUrl("/board/boardView?boNo=" + board.getBoNo());
			resultMessage.setUrlTitle("해당 뷰");
		}
		model.addAttribute("resultMessage", resultMessage);
		return "common/message";
	}

	@PostMapping("/boardRegist")
	public String boardRegist(@RequestParam(name = "boFiles", required = false) MultipartFile[] boFiles,
			@ModelAttribute("board") BoardVO board, Model model) {
		logger.info("board={}", board);
		ResultMessageVO resultMessage = new ResultMessageVO();
		System.out.println("값들 " + board);
		try {
			boardservice.registboard(board);
			resultMessage.setResult(true);
			resultMessage.setMessage("등록되었습니다.");
			resultMessage.setTitle("등록");
			if (board.getCodeParentCd().contains("BOD10")) {
				resultMessage.setUrl("/board/question/boardList");
			} else if (board.getCodeParentCd().contains("BOD20")) {
				resultMessage.setUrl("/board/free/boardList");
			} else if (board.getCodeParentCd().contains("BOD30")) {
				resultMessage.setUrl("/board/notice/boardList");
			}
			resultMessage.setUrlTitle("목록으로");
		} catch (BizNotEffectedException ene) {
			resultMessage.setResult(false);
			resultMessage.setMessage("실패: 등록 실패하였습니다.");
			resultMessage.setTitle("등록");
			if (board.getCodeParentCd().contains("BOD10")) {
				resultMessage.setUrl("/board/question/boardList");
			} else if (board.getCodeParentCd().contains("BOD20")) {
				resultMessage.setUrl("/board/free/boardList");
			} else if (board.getCodeParentCd().contains("BOD30")) {
				resultMessage.setUrl("/board/notice/boardList");
			}
			resultMessage.setUrlTitle("목록으로");
		}
		model.addAttribute("resultMessage", resultMessage);
		return "common/message";
	}

	@RequestMapping("/replyLike")
	public String replyLike(HttpServletRequest req, @ModelAttribute("replylike") ReplyLikeVO replylike) {
		HttpSession session = req.getSession();
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
		if (user != null) {
			if (replylike.getMemId() != null && replylike.getReplyNo() != 0) {
				replylikeservice.replyLike(replylike);
			}
		}
		String prePage = (String) session.getAttribute("PREPAGE");
		session.removeAttribute("PREPAGE");
		if (prePage != null) {
			return "redirect:" + prePage + "&reContentYN=Y";
		}
		return "redirect:/board/boardView?boNo=" + replylike.getBoNo();
	}

	@RequestMapping("/{parentCd}/excelDown")
	@ResponseBody
	public void excelDown(@ModelAttribute("searchVO") BoardSerchVO searchVO, @PathVariable("parentCd") String parentCd,
			HttpServletResponse resp, HttpServletRequest req) throws Exception {
		String title = "";
		if (parentCd.equals("notice")) {
			searchVO.setBoardParentCd("BOD30 ");
			title = "공지사항";
		} else if (parentCd.equals("question")) {
			searchVO.setBoardParentCd("BOD10 ");
			title = "질문";
		} else if (parentCd.equals("free")) {
			searchVO.setBoardParentCd("BOD20 ");
			title = "자유";
		}
		boardservice.excelDown(searchVO, title, resp);
	}
}
