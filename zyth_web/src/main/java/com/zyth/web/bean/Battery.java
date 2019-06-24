package com.zyth.web.bean;

import java.util.Date;

public class Battery {
    private String deveui;

    private String appeui;

    private String msgtype;

    private Integer seqnum;

    private Integer capcity;

    private Date rcvtime;

    private String rsv;

    public String getDeveui() {
        return deveui;
    }

    public void setDeveui(String deveui) {
        this.deveui = deveui == null ? null : deveui.trim();
    }

    public String getAppeui() {
        return appeui;
    }

    public void setAppeui(String appeui) {
        this.appeui = appeui == null ? null : appeui.trim();
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

    public Integer getCapcity() {
        return capcity;
    }

    public void setCapcity(Integer capcity) {
        this.capcity = capcity;
    }

    public Date getRcvtime() {
        return rcvtime;
    }

    public void setRcvtime(Date rcvtime) {
        this.rcvtime = rcvtime;
    }

    public String getRsv() {
        return rsv;
    }

    public void setRsv(String rsv) {
        this.rsv = rsv == null ? null : rsv.trim();
    }
}