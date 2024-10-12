package com.ManagerTourVietNam.model.ServiceModel;

public class ResponseObject {
    private String msg;
    private String status;
    private Object data;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResponseObject(){

    }

    public ResponseObject(String msg, String status, Object data) {
        this.msg = msg;
        this.status = status;
        this.data = data;
    }


}
