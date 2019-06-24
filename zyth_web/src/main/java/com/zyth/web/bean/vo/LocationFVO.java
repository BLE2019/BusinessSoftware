package com.zyth.web.bean.vo;

import java.io.Serializable;

import com.zyth.web.bean.LocationFmap;


public class LocationFVO extends LocationFmap implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 3500192999809473148L;
	private boolean isSos;
	/**
	 * 是否是警报
	 * @return
	 */
	public boolean isSos() {
		return isSos;
	}
	public void setSos(boolean isSos) {
		this.isSos = isSos;
	}

}
