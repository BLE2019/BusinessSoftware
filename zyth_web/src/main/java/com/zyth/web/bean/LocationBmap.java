package com.zyth.web.bean;

import java.math.BigDecimal;
import java.util.Date;

public class LocationBmap {
    private String deveui;

    private Integer userId;

    private BigDecimal mapX;

    private BigDecimal mapY;

    private Date rcvtime;

    private Integer areaId;

    private Integer isLatest;

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

    public BigDecimal getMapX() {
        return mapX;
    }

    public void setMapX(BigDecimal mapX) {
        this.mapX = mapX;
    }

    public BigDecimal getMapY() {
        return mapY;
    }

    public void setMapY(BigDecimal mapY) {
        this.mapY = mapY;
    }

    public Date getRcvtime() {
        return rcvtime;
    }

    public void setRcvtime(Date rcvtime) {
        this.rcvtime = rcvtime;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getIsLatest() {
        return isLatest;
    }

    public void setIsLatest(Integer isLatest) {
        this.isLatest = isLatest;
    }
}