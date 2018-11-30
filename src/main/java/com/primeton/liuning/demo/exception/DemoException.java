package com.primeton.liuning.demo.exception;

import org.springframework.http.HttpStatus;

import com.primeton.liuning.demo.enums.CustomEnum;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * @author liuning
 * @date 2018/11/12 14:52
 */
public class DemoException extends Exception {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -3876319857610564892L;

	private final static String ERROR_CODE = "ErrCode: ";

	private final static String ERROR_MESSAGE = "Message: ";

	/**
	 * http状态码
	 */
	private HttpStatus httpStatus = INTERNAL_SERVER_ERROR;
	/**
	 * 异常码
	 */
	private String code;
	/**
	 * 异常信息
	 */
	private String message;
	/**
	 * 返回数据
	 */
	private Object data;

	public DemoException(CustomEnum e) {
		setCode(e.getCode());
		setMessage(e.getMessage());
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public DemoException(String code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public DemoException(String message, String code, String message1, Object data) {
		super(message);
		this.code = code;
		this.message = message1;
		this.data = data;
	}

	public DemoException(String message, Throwable cause, String code, String message1, Object data) {
		super(message, cause);
		this.code = code;
		this.message = message1;
		this.data = data;
	}

	public DemoException(Throwable cause, String code, String message, Object data) {
		super(cause);
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public DemoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
			String code, String message1, Object data) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.code = code;
		this.message = message1;
		this.data = data;
	}

	public DemoException() {

	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
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
}
