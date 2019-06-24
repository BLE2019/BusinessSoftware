package com.zyth.web.bean.vo;

import java.io.Serializable;
import java.util.List;

import com.zyth.web.bean.Menu;

public class MenuVO extends Menu implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -4282337442971463450L;
	private List<MenuVO> subMenu;

	public List<MenuVO> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(List<MenuVO> subMenu) {
		this.subMenu = subMenu;
	}

}
