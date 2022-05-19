package com.soap.consume.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.soap.consume.stub.StoragePlanMessage;
import com.soap.consume.stub.StoragePlanMessageResponse;

public class SoapClient extends WebServiceGatewaySupport{

	public StoragePlanMessageResponse getStoragePlanMessageResponse(StoragePlanMessage request) {
		return (StoragePlanMessageResponse) getWebServiceTemplate().marshalSendAndReceive(request);
	}
}
