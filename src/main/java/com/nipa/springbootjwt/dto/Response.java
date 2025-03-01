package com.nipa.springbootjwt.dto;


public class Response {
    private Integer statusCode;
    private String status;
    private String message;
    private Object content;

    public Response() {
    }


    public Response(Integer statusCode, String status, String message, Object content) {
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
        this.content = content;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
