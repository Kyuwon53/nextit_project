package com.mayday.common.web;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mayday.common.vo.ResultMessageVO;
import com.mayday.exception.BizNotEffectedException;
import com.mayday.exception.BizNotFoundException;

@ControllerAdvice
public class ErrorController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(BizNotFoundException.class)
	public String errBizNotFoundException(BizNotFoundException ex, Model model) {
		logger.debug("error={}", ex);
		ResultMessageVO resultMessage = new ResultMessageVO();
		resultMessage.setResult(false);
		resultMessage.setMessage("실패: 해당 글을 찾을 수 없습니다.");
		resultMessage.setTitle("수정");
		resultMessage.setCode("Not Found Exception");
		model.addAttribute("resultMessage", resultMessage);
		return "common/message";
	}
	@ExceptionHandler(BizNotEffectedException.class)
	public String errBizNotEffectedException(BizNotEffectedException ex, Model model) {
		logger.debug("error={}", ex);
		ResultMessageVO resultMessage = new ResultMessageVO();
		resultMessage.setResult(false);
		resultMessage.setMessage("실패: 해당 글을 찾을 수 없습니다.");
		resultMessage.setTitle("수정");
		resultMessage.setCode("Not Effect Exception");
		model.addAttribute("resultMessage", resultMessage);
		return "common/message";
	}
	
	@ExceptionHandler(SQLException.class)
	public String errSQLException(SQLException ex, Model model) {
		logger.debug("error={}", ex);
		ResultMessageVO resultMessage = new ResultMessageVO();
		resultMessage.setResult(false);
		resultMessage.setMessage("시스템 오류입니다.");
		resultMessage.setTitle("실패");
		resultMessage.setCode("ERROR");
		model.addAttribute("resultMessage", resultMessage);
		return "common/message";
	}
	
	@ExceptionHandler(NullPointerException.class)
	public String errNullPointerException(NullPointerException ex, Model model) {
		logger.debug("error={} ", ex);
		ResultMessageVO resultMessage = new ResultMessageVO();
		resultMessage.setResult(false);
		resultMessage.setMessage("값이 없습니다.");
		resultMessage.setTitle("실패");
		resultMessage.setCode("NULL");
		model.addAttribute("resultMessage", resultMessage);
		return "common/message";
	}
}
