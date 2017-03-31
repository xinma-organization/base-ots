package com.xinma.base.ots;

import com.xinma.base.core.error.CustomError;

/**
 * OtsError错误代码定义
 * 
 * @author Alauda
 *
 * @date 2015年6月29日
 *
 */
public enum OtsError implements CustomError {

	UnknownErr("ots-001", "Unknown excption when access ots storage."),

	NetWorkErr("ots-002", "Network excption when access ots storage."),

	OTSClientExceptionErr("ots-003", "OTSClient excption when access ots storage."),

	OTSExceptionErr("ots-004", "OTSExcption when access ots storage."),

	OtsConditionCheckFailErr("ots-005", "ots condition check fail error."),

	ReachRetryLimitErr("ots-006", "reach ots retry limit error."),

	BatchWriteRowErr("ots-007", "Ots BatchWriteRow error."),

	JsonProcessingExceptionErr("ots-008", "Ots operation JsonProcessingException error.");

	String value;

	String description;

	OtsError(String value, String description) {
		this.value = value;
		this.description = description;
	}

	@Override
	public String value() {
		return value;
	}

	@Override
	public String description() {
		return description;
	}

}
