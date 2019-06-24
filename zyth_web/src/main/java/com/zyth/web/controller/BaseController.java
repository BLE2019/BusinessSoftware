package com.zyth.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.zyth.web.common.basic.MyStringTrimmerEditor;
import com.zyth.web.common.util.DateTimeUtils;
import com.zyth.web.common.util.StringUtils;


/**
 * Crontroller类的基础类，可以 除掉参数中的空格
 *
 *
 */
public class BaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(BaseController.class);

	public static Integer shopId = 0;

	/**
	 * 日期时间格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String dateTimeString = "yyyy-MM-dd HH:mm:ss";

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				DateTimeUtils.dateTimeString);
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(
				Integer.class, true));
		binder.registerCustomEditor(String.class, new MyStringTrimmerEditor(true));
	}

	/**
	 * 校验绑定对象
	 */
	protected BindException validateRequestBean(Validator validator, Object entity)
			throws Exception {
		Class<? extends Object> target = entity.getClass();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(entity,StringUtils.firstCharToLow(target.getSimpleName()));
		BindException errors = new BindException(binder.getBindingResult());
		validator.validate(entity, errors);
		return errors;
	}


	/**
	 * 初始化输出
	 * @param fileName
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected PrintWriter initPrintWriter(String fileName,HttpServletResponse response) throws Exception{
		   response.setContentType("application/octet-stream;charset=GBK");
		   response.setHeader("Content-Disposition","attachment;  filename="+fileName);
		   PrintWriter out = response.getWriter();//放在第一句是会出现乱码
		   return out;
	}

	/**
	 * 输出数据
	 * @param writer
	 * @param data
	 * @param isEnd
	 * @throws Exception
	 */
	protected void writeData(PrintWriter writer ,String data , boolean isEnd) throws Exception{
		writer.write(data);
		writer.flush();
		if(isEnd){
			writer.close();
		}
	}

	/**
	 * 根据request取得IP
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        String[] ips = ip.split(",");

        if(ips.length>0){
        	ip=ips[0];
        }

        return ip;
    }
}

