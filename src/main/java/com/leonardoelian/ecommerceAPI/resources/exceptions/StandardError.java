package com.leonardoelian.ecommerceAPI.resources.exceptions;

import java.util.Date;

public class StandardError {

    private Integer status;
    private String msg;
    private Date timeStamp;
    private String message;
    private String path;

    public StandardError(Integer status, String msg, Date timeStamp, String message, String path) {
        this.status = status;
        this.msg = msg;
        this.timeStamp = timeStamp;
        this.message = message;
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
