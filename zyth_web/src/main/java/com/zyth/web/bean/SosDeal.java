package com.zyth.web.bean;

import java.io.Serializable;
import java.util.Date;

import com.zyth.web.bean.vo.PageVO;

public class SosDeal extends PageVO implements Serializable {
    /**
	 *
	 */
	private static final long serialVersionUID = -6900808939010526118L;

	private String deveui;

    private String msgtype;

    private Integer seqnum;

    private Date rcvtime;

    private Date dealTime;

    private String dealInfo;

    private Integer dealStatus;

    private Integer areaId;

    public String getDeveui() {
        return deveui;
    }

    public void setDeveui(String deveui) {
        this.deveui = deveui == null ? null : deveui.trim();
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype == null ? null : msgtype.trim();
    }

    public Integer getSeqnum() {
        return seqnum;
    }

    public void setSeqnum(Integer seqnum) {
        this.seqnum = seqnum;
    }

    public Date getRcvtime() {
        return rcvtime;
    }

    public void setRcvtime(Date rcvtime) {
        this.rcvtime = rcvtime;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public String getDealInfo() {
        return dealInfo;
    }

    public void setDealInfo(String dealInfo) {
        this.dealInfo = dealInfo == null ? null : dealInfo.trim();
    }

    public Integer getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(Integer dealStatus) {
        this.dealStatus = dealStatus;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }
}