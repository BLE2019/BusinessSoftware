package com.zyth.web.common.constants;

/**
 * 一般常量
 *
 */
public class CommonConstants {
	/**
     * 每页显示记录条数 ，默认是每页显示10条记录
     */
	public final static int ONE_PAGE_COUNT = 10;

	/**
	 * 前台分页显示页数
	 */
	public final static int VIEW_PAGE_COUNT = 7;


	/**
	 * 后台管理系统session key
	 */
	public static final String OS_SESSION_KEY = "FS_SYSTEM_SESSION_ID";

	/**
	 * 登录用户在 SESSION中保存的 key
	 */
	public static final String LOGIN_USER_KEY = "LOGIN_USER_KEY_";
	/**
	 * 后台管理系统session 过期时间为 半小时
	 */
	public static final Integer OS_SESSION_TIMEOUT = 30 * 60;


}
