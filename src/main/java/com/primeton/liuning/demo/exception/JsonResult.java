package com.primeton.liuning.demo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.primeton.liuning.demo.enums.CustomEnum;
import com.primeton.liuning.demo.model.UserEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "JsonResult", description = "返回信息")
public class JsonResult<T> implements Serializable {
	private static final long serialVersionUID = 7023397262788301448L;

	public static final String SUCCESS = "0";

	@ApiModelProperty(value = "返回状态码，0表示成功，其他失败")
	private String state;

	@ApiModelProperty(value = "返回信息")
	private String message;

	@ApiModelProperty(value = "系统异常时，返回的具体异常信息")
	private String error;

	@ApiModelProperty(value = "相关参数信息")
	private Object[] params;// NOSONAR

	@ApiModelProperty(value = "返回数据")
	private T data;// NOSONAR

	public JsonResult() {
		this.state = SUCCESS;
	}

	public JsonResult(String state, String msg) {
		this.state = state;
		this.message = msg;
	}

	public JsonResult(String state, String msg, String error, Object[] params, T data) {
		this.state = state;
		this.message = msg;
		this.error = error;
		this.params = params;
		this.data = data;
	}

	public JsonResult(T data) {
		this(SUCCESS, null, null, null, data);
	}

	public JsonResult(Object[] params, T data) {
		this(SUCCESS, null, null, params, data);
	}

	public JsonResult(Exception e) {
		// 业务异常时的返回处理
		if (e instanceof DemoException) {
			DemoException se = (DemoException) e;
			this.state = se.getCode();
			this.message = se.getMessage();
			this.params = (Object[]) se.getData();
		}
		// 参数异常时的返回处理
		else if (e instanceof MissingServletRequestParameterException) {
			this.state = CustomEnum.ERROR_LACK_PARAM.getCode();
			this.message = CustomEnum.ERROR_LACK_PARAM.getMessage();
			this.error = e.toString();
		}
		// 系统异常时的返回处理
		else {
			this.state = CustomEnum.SYSTEM_ERROR.getCode();
			this.message = e.toString();
//            this.error = Utils.getStackTraceInfo(e);
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
