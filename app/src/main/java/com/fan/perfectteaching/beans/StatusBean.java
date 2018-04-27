package com.fan.perfectteaching.beans;

public class StatusBean {

    /**
     * msg : 注册成功
     * isOk : true
     * isFail : false
     */

    private String msg;
    private boolean isOk;
    private boolean isFail;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public boolean isFail() {
        return isFail;
    }

    public void setFail(boolean fail) {
        isFail = fail;
    }

}