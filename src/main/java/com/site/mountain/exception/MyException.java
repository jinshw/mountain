package com.site.mountain.exception;

public class MyException extends RuntimeException {
    private Integer code;


    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
