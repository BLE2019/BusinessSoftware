package com.zyth.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyth.web.bean.vo.MenuVO;
import com.zyth.web.mapper.MenuMapper;
import com.zyth.web.service.OperatorServiceApi;


@Service("operatorService")
public class OperatorService implements OperatorServiceApi {

	@Resource
	MenuMapper menuMapper;

	public List<MenuVO> getMenuList() {
		List<MenuVO> parentMenu = menuMapper.selectByLevel("1");
		for (MenuVO menuVO : parentMenu) {
			List<MenuVO> subMenu = menuMapper.selectByParent(menuVO.getMenuNo());
			menuVO.setSubMenu(subMenu);
		}
		return parentMenu;
	}

}
