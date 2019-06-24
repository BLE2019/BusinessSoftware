package com.zyth.web.common.basic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * 自定义Session类
 * @author Tommy
 *
 */
public class SystemSession implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * session数据存储map
	 */
	private Map<String,Object> sessionData = new HashMap<String,Object>();
	/**
	 * sessionId  session标识
	 */
	private String sessionId = null;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Object getAttribute(String name){
		Object value = sessionData.get(name);
		return value;
	}

	public void setAttribute(String name,Object value){
		sessionData.put(name, value);
	}

	public void removeAttribute(String name){
		sessionData.remove(name);
	}

	public void removeAllAttribute(){
		sessionData.clear();
	}

	public void disable(){
	}

	public boolean hasAttributeName(String name){
		return sessionData.containsKey(name);
	}

	public Set<String> getAttributeNames(){
		return sessionData.keySet();
	}

	public Map<String, Object> getSessionData() {
		return sessionData;
	}

	public void setSessionData(Map<String, Object> sessionData) {
		this.sessionData = sessionData;
	}

}
