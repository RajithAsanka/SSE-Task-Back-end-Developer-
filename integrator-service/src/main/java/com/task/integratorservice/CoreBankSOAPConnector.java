package com.task.integratorservice;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

/**
 * Developed by P G R Asanka - 901833109V
 */

public class CoreBankSOAPConnector extends WebServiceGatewaySupport {

    public Object callWebService(String url, Object request) {
        return getWebServiceTemplate().marshalSendAndReceive(url, request);
    }
}
