package com.youxiu326.exception;

/**
 * 自定义No Found异常
 * <p>Title: NoFoundExcepiton</p>
 * @author  Liyan
 * @date    2018年1月11日 下午3:36:13
 */
public class NoFoundExcepiton extends Exception {

    private static final long serialVersionUID = -5955607821816077172L;

    public NoFoundExcepiton(String errorInfo) {
        super(errorInfo);
    }
}
