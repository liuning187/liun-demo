package com.primeton.liuning.demo.dto;


import java.io.Serializable;

import com.primeton.liuning.demo.model.UserEntity;

/**
 * @author liuning
 * @date 2018/11/8 13:52
 */
public class UserDTO extends UserEntity implements Serializable {
    /**
     * 组织名称
     */
    private String orgName;
    /**
     * 组织简称
     */
    private String orgShortName;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgShortName() {
        return orgShortName;
    }

    public void setOrgShortName(String orgShortName) {
        this.orgShortName = orgShortName;
    }
}
