package com.zyth.web.common.basic;

import java.util.UUID;

import org.springframework.data.redis.core.RedisTemplate;

import com.zyth.web.common.util.SpringUtils;



/**
 * 自定义Session的工具类
 * @author Tommy
 *
 */
public class SessionManager {


	/**
	 * 通过uuid生成sessionID
	 * @return
	 */
	private static String generateSessionKey(){
		String sessionKey = UUID.randomUUID().toString();
		return sessionKey.replace("-","");
	}

	/**
	 * 删除session,使其失效
	 * @param sessionKey
	 */
	public static void deleteSession(String sessionKey){
		RedisTemplate<String,Object> redisTemplate = getRedisTemplate();
		redisTemplate.delete(sessionKey);
	}

	public static RedisTemplate<String,Object> getRedisTemplate(){
		@SuppressWarnings("unchecked")
		RedisTemplate<String,Object> redisTemplate = (RedisTemplate<String,Object>)SpringUtils.getBean("redisTemplate");
		return redisTemplate;
	}


}
