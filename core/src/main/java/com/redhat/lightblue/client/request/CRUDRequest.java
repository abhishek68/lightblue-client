package com.redhat.lightblue.client.request;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;

import com.redhat.lightblue.client.http.HttpMethod;
import com.redhat.lightblue.client.Operation;

public abstract class CRUDRequest extends LightblueDataRequest {
    
    public CRUDRequest(HttpMethod method, String operationName, String entityName, String entityVersion) {
        super(method,operationName, entityName, entityVersion);
    }

    public abstract Operation getOperation();
}
