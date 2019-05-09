package com.youxiu326.exception;

/**
 * 异常枚举类
 * <p>Title: ExceptionEnum</p>
 * @author  Liyan
 * @date    2018年1月11日 下午3:54:48
 */
public enum ExceptionEnum {

    ERROR_NOFOUND("无法找到相应的数据");

    private String value;

    public String getValue() {
        return value;
    }

    ExceptionEnum(String value){
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
