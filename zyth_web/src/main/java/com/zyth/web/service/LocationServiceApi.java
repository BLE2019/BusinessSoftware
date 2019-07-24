package com.zyth.web.service;

import java.util.List;

import com.zyth.web.bean.vo.LocationFVO;

public interface LocationServiceApi {
	public void sysInit();

	public List<LocationFVO> getHistory(LocationFVO locationFVO);
}
