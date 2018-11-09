package net.stelmaszak.tweedit.filter;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ReqInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String browser;
    private Date dataCzas;
    private int reqTime;
    private String ipAddress;
    private String servletPath;

    public ReqInfo() {
    }

    public ReqInfo(String browser, Date dataCzas, int reqTime, String ipAddress, String servletPath) {
        this.browser = browser;
        this.dataCzas = dataCzas;
        this.reqTime = reqTime;
        this.ipAddress = ipAddress;
        this.servletPath = servletPath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public Date getDataCzas() {
        return dataCzas;
    }

    public void setDataCzas(Date dataCzas) {
        this.dataCzas = dataCzas;
    }

    public int getReqTime() {
        return reqTime;
    }

    public void setReqTime(int reqTime) {
        this.reqTime = reqTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getServletPath() {
        return servletPath;
    }

    public void setServletPath(String servletPath) {
        this.servletPath = servletPath;
    }

    @Override
    public String toString() {
        return "ReqInfo{" +
                "id=" + id +
                ", browser='" + browser + '\'' +
                ", dataCzas=" + dataCzas +
                ", reqTime=" + reqTime +
                ", ipAddress='" + ipAddress + '\'' +
                ", servletPath='" + servletPath + '\'' +
                '}';
    }

}