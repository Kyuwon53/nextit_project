package com.mayday.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PrePageSave extends HandlerInterceptorAdapter{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception{
		HttpSession session = req.getSession(true);
		String preP = (String)session.getAttribute("preP"); 
		// GET 으로 요청된 경우  preP 제거 
		logger.info("preP = {}", preP);
		logger.info("req.getMethod() = {}", req.getMethod());
		logger.info("RequestMethod.GET = {}", RequestMethod.GET);
		if( preP != null && req.getMethod().equals("GET")) {
			logger.info("preP 제거 ");
			session.removeAttribute("preP");
			preP = null;
		}
		
		if( preP == null) {
			String headerReferer = req.getHeader("Referer");
			session.setAttribute("preP", headerReferer);
			logger.info("preP 존재하지 않아서 preP에 [{}] 를 지정 ", headerReferer);
		}
		logger.debug("저장된 preP에 [{}] ", preP);		
		return true;
	}
}
