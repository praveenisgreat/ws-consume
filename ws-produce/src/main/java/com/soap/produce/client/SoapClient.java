package com.soap.produce.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.soap.produce.stub.ZStorageResultMessage;
import com.soap.produce.stub.ZStorageResultMessageResponse;

public class SoapClient extends WebServiceGatewaySupport{

	public ZStorageResultMessageResponse getStoragePlanMessageResponse(ZStorageResultMessage request) {
		return (ZStorageResultMessageResponse) getWebServiceTemplate().marshalSendAndReceive(request);
	}
}
