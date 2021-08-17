package com.mayday.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession(false);
		String ajax = request.getHeader("X-requested-with");
		if(session==null) {
			if(ajax!=null) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				return false;
			}
			response.sendRedirect(request.getContextPath()+"/login/login");
			return false;
		}
		if(session.getAttribute("USER_INFO")==null) {
			if(ajax!=null) { //로그인 x ajax일때
				session.setAttribute("PREPAGE", request.getHeader("Referer"));
				session.setAttribute("reContent", request.getParameter("reContent"));
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				return false;
			}
			
			session.setAttribute("PREPAGE", request.getHeader("Referer"));		
			response.sendRedirect(request.getContextPath() +"/login/login");
			return false;
		}
		return true;
	}
	
}
