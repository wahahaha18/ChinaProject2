package com.ycl.car.model;

/**
 * Created by y11621546 on 2017/2/15.
 */

public class TpmCheck {

    /**
     * tpmid : 2
     * systemname : 系统1
     * jcd : 检查点2
     * eqno : eq_01
     * tmethod : 点检方2
     * workinfo : 工作内容2
     * tstatus : 开机
     * plandate : 计划制定中
     * status : -1
     */

    private int tpmid;
    private String systemname;
    private String jcd;
    private String eqno;
    private String tmethod;
    private String workinfo;
    private String tstatus;
    private String plandate;
    private int status;

    public int getTpmid() {
        return tpmid;
    }

    public void setTpmid(int tpmid) {
        this.tpmid = tpmid;
    }

    public String getSystemname() {
        return systemname;
    }

    public void setSystemname(String systemname) {
        this.systemname = systemname;
    }

    public String getJcd() {
        return jcd;
    }

    public void setJcd(String jcd) {
        this.jcd = jcd;
    }

    public String getEqno() {
        return eqno;
    }

    public void setEqno(String eqno) {
        this.eqno = eqno;
    }

    public String getTmethod() {
        return tmethod;
    }

    public void setTmethod(String tmethod) {
        this.tmethod = tmethod;
    }

    public String getWorkinfo() {
        return workinfo;
    }

    public void setWorkinfo(String workinfo) {
        this.workinfo = workinfo;
    }

    public String getTstatus() {
        return tstatus;
    }

    public void setTstatus(String tstatus) {
        this.tstatus = tstatus;
    }

    public String getPlandate() {
        return plandate;
    }

    public void setPlandate(String plandate) {
        this.plandate = plandate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
