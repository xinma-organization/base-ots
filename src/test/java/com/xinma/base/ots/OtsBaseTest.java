package com.xinma.base.ots;

import com.aliyun.openservices.ots.OTSClient;

public class OtsBaseTest {
	private String otsEndpoint = "http://touyun-dev.cn-hangzhou.ots.aliyuncs.com";
	private String accessId = "jFGmMqmS8LZABfRB";
	private String accessKey = "PNnhx6TpPRdVbBE94xLGqX0zTrZrMM";
	private String instanceName = "tm-dev";

	protected OTSClient otsClient = new OTSClient(otsEndpoint, accessId, accessKey, instanceName);
}
