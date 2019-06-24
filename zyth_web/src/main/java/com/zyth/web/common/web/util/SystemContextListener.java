package com.zyth.web.common.web.util;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zyth.web.common.basic.SessionManager;
import com.zyth.web.common.util.SpringUtils;

/**
 * 系统启动加载类。所有需要初始化的数据请放在这里。
 * @author user
 *
 */
public class SystemContextListener extends ContextLoaderListener {

	@Override
	public void contextInitialized ( ServletContextEvent sce )
	{
		super.contextInitialized(sce);
		SpringUtils.setApplicationContext((WebApplicationContext) sce.getServletContext().
				getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE));
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}
}
