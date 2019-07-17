package com.zyth.web.bean;

import java.io.Serializable;

import com.zyth.web.bean.vo.PageVO;

public class User extends PageVO implements Serializable {
    /**
	 *
	 */
	private static final long serialVersionUID = 6240379922298815598L;

	private Integer userId;

    private String userName;

    private String userType;

    private Integer sex;

    private String deveui;

    private int capcity;
    private int isOnline;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getDeveui() {
        return deveui;
    }

    public void setDeveui(String deveui) {
        this.deveui = deveui == null ? null : deveui.trim();
    }

	public int getCapcity() {
		return capcity;
	}

	public void setCapcity(int capcity) {
		this.capcity = capcity;
	}

	public int getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(int isOnline) {
		this.isOnline = isOnline;
	}
}