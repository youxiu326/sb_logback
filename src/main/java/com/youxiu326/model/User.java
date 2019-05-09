package com.youxiu326.model;

import java.io.Serializable;

/**
 * 员工实体类
 * <p>Title: User</p>
 * @author  Liyan
 * @date    2018年1月11日 上午10:45:20
 */
public class User implements Serializable{

    private static final long serialVersionUID = 1L;

    /** 自增主键  */
    private Integer id;

    /** 员工工号  */
    private String userId;

    /** 员工姓名 */
    private String userName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
