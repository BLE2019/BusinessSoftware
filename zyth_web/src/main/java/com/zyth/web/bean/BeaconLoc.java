package com.zyth.web.bean;

import java.io.Serializable;

import com.zyth.web.bean.vo.PageVO;

public class BeaconLoc extends PageVO implements Serializable {
    /**
	 *
	 */
	private static final long serialVersionUID = 3869736516603960565L;

	private String beaconId;

    private Integer mapX;

    private Integer mapY;

    private Integer mapZ;

    public String getBeaconId() {
        return beaconId;
    }

    public void setBeaconId(String beaconId) {
        this.beaconId = beaconId == null ? null : beaconId.trim();
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
}