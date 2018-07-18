package com.ms.base.domain;

import com.alibaba.fastjson.JSONObject;

/**
 * @author JamelHuang
 * @date 2018/3/2
 */
public class Reply {
    private Object results;
    private Integer statusCode;
    private String statusMsg;
    private Boolean success;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getResults() {
        return results;
    }

    public void setResults(Object results) {
        this.results = results;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public Reply(Object results, Integer statusCode,String statusMsg, Boolean success){
        this.results = results;
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
        this.success = success;
    }

    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("results",results==null?"{}":results.toString());
        object.put("statusCode",statusCode);
        object.put("statusMsg",statusMsg);
        object.put("success",success);
        return object.toString();
    }
}
