package com.mayday.common.util;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {
	private Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();

	public CookieUtil(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
	}

	public Cookie getCookie(String name) {
		return cookieMap.get(name);
	}// cookie 이름으로 쿠키 조회, 있으면 리턴, 없으면 널 리턴

	public String getValue(String name) throws IOException {
		Cookie cookie = getCookie(name);
		if (cookie == null) {
			return null;
		}
		return URLDecoder.decode(cookie.getValue(), "UTF-8");
	}// 해당 이름의 쿠키가 있으면 값 리턴, 없으면 널 리턴

	public boolean exists(String name) {
		return cookieMap.get(name) != null;
	}// 쿠키가 있으면 true, 없으면 false

	// 여기까지는 CookieUtil 객체를 생성해야 사용 가능한 메소드

	// 밑에서부터는 CookieUtil 객체 생성 안 해도 되는 것들
	
	public static Cookie createCookie(String name, String value) throws IOException {
		return createCookie(name, value, "", -1);
	}

	public static Cookie createCookie(String name, String value, String path) throws IOException {
		return createCookie(name, value, path, -1);
	}

	public static Cookie createCookie(String name, String value, int maxAge) throws IOException {
		return createCookie(name, value, "", maxAge);
	}

	public static Cookie createCookie(String name, String value, String path, int maxAge) throws IOException {

		Cookie cookie = new Cookie(name, value);
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);

		return cookie;

	}

}