package com.xinma.base.ots;

import com.xinma.base.core.error.CustomError;
import com.xinma.base.core.exceptions.CustomException;

/**
 * Table存储访问异常，继承自CustomException
 * 
 * @author Alauda
 *
 * @date 2015年5月19日
 *
 */
public class OtsException extends CustomException {

	private static final long serialVersionUID = -8111207025814059578L;

	public OtsException() {
		super();
	}

	public OtsException(String message) {
		super(message);
	}

	public OtsException(String message, Throwable cause) {
		super(message, cause);
	}

	public OtsException(Throwable cause) {
		super(cause);
	}

	public OtsException(CustomError error, String... params) {
		super(error, params);
	}

	public OtsException(Throwable cause, CustomError error, String... params) {
		super(cause, error, params);
	}
}
