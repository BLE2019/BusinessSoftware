package com.zyth.web.common.web.util;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zyth.web.service.LocationServiceApi;


@SuppressWarnings("serial")
public class InitServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		LocationServiceApi locationService = (LocationServiceApi)context.getBean("locationService");
		locationService.sysInit();
	}

}
