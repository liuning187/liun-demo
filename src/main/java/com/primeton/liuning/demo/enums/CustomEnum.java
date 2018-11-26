package com.primeton.liuning.demo.enums;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义异常枚举类
 * @author 刘宁
 *
 */
public enum CustomEnum {
	SYSTEM_ERROR("00000","系统错误"),
	ERROR_FORMATUSER("001","用户名或密码不能为空"),
	ERROR_FORMATPASSWORD("002", "用户名或密码错误"),
	ERROR_FORMATUSERID("003","id不能为空"),
	ERROR_FORMATOGRIDNULL("005","组织ID不能为空"),
	ERROR_FORMATOGRNULL("021","组织名称或简称不能为空"),
	ERROR_FORMGORISNULL("022","id不能为空"),
	ERROR_FORMGORISUSER("023","组织下有用户,不允许删除"),
	ERROR_FORMGORISORG("024","组织下有子组织,不允许删除"),
	ERROR_LACK_PARAM("030","缺少必传参数错误，请检查");
	
	private String code;
	
	private String message;
	
	private Object data;
	
	CustomEnum(String code,String message) {
		this.code=code;
		this.message=message;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
