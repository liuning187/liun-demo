package com.primeton.liuning.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
/**
 * 组织机构实体类
 * @author Administrator
 *
 */
public class OrgEntity implements Serializable {
	/**
	 * 组织ID
	 */
	private Integer orgId;
	/**
	 * 组织名称
	 */
	private String orgName;
	/**
	 * 组织简称
	 */
	private String orgShortName;
	/**
	 * 父ID
	 */
	private Integer parentId;

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getOrgShortName() {
		return orgShortName;
	}

	public void setOrgShortName(String orgShortName) {
		this.orgShortName = orgShortName;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
