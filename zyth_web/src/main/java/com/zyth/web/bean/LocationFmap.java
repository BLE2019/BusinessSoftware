package com.zyth.web.bean;

import java.util.Date;

public class LocationFmap {
    protected String deveui;

    protected Integer userId;

    protected Integer mapX;

    protected Integer mapY;

    protected Integer mapZ;

    protected Date rcvtime;

    protected Integer isLatest;

    protected Integer areaId;

    public String getDeveui() {
        return deveui;
    }

    public void setDeveui(String deveui) {
        this.deveui = deveui == null ? null : deveui.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMapX() {
        return mapX;
    }

    public void setMapX(Integer mapX) {
        this.mapX = mapX;
    }

    public Integer getMapY() {
        return mapY;
    }

    public void setMapY(Integer mapY) {
        this.mapY = mapY;
    }

    public Integer getMapZ() {
        return mapZ;
    }

    public void setMapZ(Integer mapZ) {
        this.mapZ = mapZ;
    }

    public Date getRcvtime() {
        return rcvtime;
    }

    public void setRcvtime(Date rcvtime) {
        this.rcvtime = rcvtime;
    }

    public Integer getIsLatest() {
        return isLatest;
    }

    public void setIsLatest(Integer isLatest) {
        this.isLatest = isLatest;
    }

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
}