package com.zyth.web.common.web.interceptor;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zyth.web.common.basic.SessionManager;
import com.zyth.web.common.basic.SystemSession;
import com.zyth.web.common.constants.CommonConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 判断用户权限，未登录用户跳转到登录页面
 * @author Tommy
 *
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

	private List<String> excludedUrls;

	private static final Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if(excludedUrls != null){
			String requestUri = request.getRequestURI();
		    for (String url : excludedUrls) {
		      if (requestUri.endsWith(url)) {
		        return true;
		      }
		    }
		}
		//Session设置
//		Operator session = SessionManager.getSession(request, response);
//		if(session == null ) {
//			response.getWriter().print("notLogin");
//			return false;
//		}
		//TODO 检查用户是否有访问权限

		//request.setAttribute(CommonConstants.OS_SESSION_KEY, session);
		return super.preHandle(request, response, handler);
	}

	public void setExcludedUrls(List<String> excludedUrls) {
		this.excludedUrls = excludedUrls;
	}

}
