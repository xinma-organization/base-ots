package com.xinma.base.ots;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.openservices.ots.ClientException;
import com.aliyun.openservices.ots.OTSErrorCode;
import com.aliyun.openservices.ots.OTSException;

class OtsExceptionUtils {

	private static Logger logger = LoggerFactory.getLogger(OtsExceptionUtils.class);

	/**
	 * 判断Exception对象是否为OTS可重试的异常类型
	 * 
	 * @param e
	 *            Exception Object
	 * @return 当前e为可重试异常时，返回true;否则返回false
	 */
	protected static boolean isRetryException(Exception e) {

		if (e instanceof SocketTimeoutException || e instanceof UnknownHostException) {
			logger.error("访问OTS服务时，发生SocketTimeoutException.", new OtsException(e, OtsError.NetWorkErr));

			return true;
		} else if (e instanceof OTSException) {
			logger.error("访问OTS服务时，发生OTSException.",
					new OtsException(e, OtsError.OTSExceptionErr, ((OTSException) e).getErrorCode()));

			String errCode = ((OTSException) e).getErrorCode();
			if (errCode.equals(OTSErrorCode.NOT_ENOUGH_CAPACITY_UNIT) || errCode.equals(OTSErrorCode.SERVER_UNAVAILABLE)
					|| errCode.equals(OTSErrorCode.STORAGE_TIMEOUT)
					|| errCode.equals(OTSErrorCode.PARTITION_UNAVAILABLE) || errCode.equals(OTSErrorCode.SERVER_BUSY)
					|| errCode.equals(OTSErrorCode.TABLE_NOT_READY)
					|| errCode.equals(OTSErrorCode.ROW_OPERATION_CONFLICT)
					|| errCode.equals(OTSErrorCode.INTERNAL_SERVER_ERROR)) {
				return true;
			}

		} else if (e instanceof ClientException) {
			logger.error("访问OTS服务时，发生ClientException.",
					new OtsException(e, OtsError.OTSClientExceptionErr, ((ClientException) e).getErrorCode()));
			return true;
		} else {
			logger.error("访问OTS服务时，发生未知类型错误.", new OtsException(e, OtsError.UnknownErr));
		}

		return false;
	}

	/**
	 * 判断Exception对象是否为OTSConditionCheckFail类型的异常
	 * 
	 * @param e
	 *            Exception Object
	 * @return e为OTSConditionCheckFail异常时返回true，否则返回false
	 */
	protected static boolean isOTSConditionCheckFailException(Exception e) {
		if (e instanceof OTSException) {
			String errCode = ((OTSException) e).getErrorCode();
			if (errCode.equals(OTSErrorCode.CONDITION_CHECK_FAIL)) {
				logger.error("访问OTS服务时，发生CONDITION_CHECK_FAIL类型的OTSException.",
						new OtsException(e, OtsError.OtsConditionCheckFailErr), ((OTSException) e).getErrorCode());
				return true;
			} else {
				logger.error("访问OTS服务时，发生OTSException.", new OtsException(e, OtsError.OTSExceptionErr));
			}
		} else if (e instanceof SocketTimeoutException || e instanceof UnknownHostException) {
			logger.error("访问OTS服务时，发生SocketTimeoutException.", new OtsException(e, OtsError.NetWorkErr));

		} else if (e instanceof ClientException) {
			logger.error("访问OTS服务时，发生ClientException.", new OtsException(e, OtsError.OTSClientExceptionErr),
					((ClientException) e).getErrorCode());
		} else {
			logger.error("访问OTS服务时，发生未知类型错误.", new OtsException(e, OtsError.UnknownErr));
		}

		return false;
	}
}
