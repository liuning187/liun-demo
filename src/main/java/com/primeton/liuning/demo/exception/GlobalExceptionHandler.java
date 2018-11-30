package com.primeton.liuning.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.primeton.liuning.demo.enums.CustomEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

/**
 * @author liuning
 * @date 2018/11/07 14:42
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<JsonResult> exceptionHandler(HttpServletRequest request, Exception e, DemoException me)
			throws Exception {
		ResponseEntity<JsonResult> re = null;
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		if (e instanceof DemoException) {
			re = new ResponseEntity<>(new JsonResult(me.getCode(), me.getMessage()),
					((DemoException) e).getHttpStatus());
			logger.error("异常编码:" + me.getCode(), e.getMessage(), e);
		} else {
			re = new ResponseEntity<>(new JsonResult(e), httpStatus);
		}
		return re;
	}
}
