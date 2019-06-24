package com.zyth.web.common.util;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;


/**
 * 消息内容获取
 * @author tao.j
 * @date 2015.11.11
 *
 */
public class MessageUtils {

	private static final Logger logger = Logger.getLogger(MessageUtils.class);


	private static Properties messageProperties;
	// 初始化
	public static void init(){
		try {
			messageProperties =  PropertiesLoaderUtils.loadProperties(new ClassPathResource("/config/formal/formal2/formal/message.properties"));
		} catch (IOException e) {
			logger.error("消息文件加载异常！",e);
		}
	}

	/**
	 * 获取消息内容
	 * @param msgId 消息ID
	 * @param fields 消息字段
	 * @return
	 */
	public static String getMessage(String msgId, String... fields){
		String msgContent = null;
		try {
			// 未加载
			if (messageProperties == null) {
				init();
			}

			msgContent = messageProperties.getProperty(msgId);
		} catch (Exception e) {
			msgContent = "发生未知错误，请联系管理员";
			logger.error("没有对应消息ID:!" + msgId,e);
		}
		String result = "";
		if(!StringUtils.isStrNull(msgContent)){
			result = MessageFormat.format(msgContent, fields);
		}
		return result;
	}
}
