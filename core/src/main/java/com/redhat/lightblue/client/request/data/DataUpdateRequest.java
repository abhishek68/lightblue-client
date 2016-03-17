package com.redhat.lightblue.client.request.data;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.redhat.lightblue.client.Operation;
import com.redhat.lightblue.client.Projection;
import com.redhat.lightblue.client.Query;
import com.redhat.lightblue.client.Range;
import com.redhat.lightblue.client.Update;
import com.redhat.lightblue.client.http.HttpMethod;
import com.redhat.lightblue.client.request.AbstractLightblueDataRequest;

public class DataUpdateRequest extends AbstractLightblueDataRequest {

    private Projection projection;
    private Update update;
    private Query query;
    private Range range;

    public DataUpdateRequest(String entityName, String entityVersion) {
        super(entityName, entityVersion);
    }

    public DataUpdateRequest(String entityName) {
        super(entityName);
    }

    public void returns(List<? extends Projection> projection) {
        this.projection = Projection.project(projection);
    }

    public void returns(Projection... projection) {
        this.projection = Projection.project(projection);
    }

    public void where(Query queryExpression) {
        query = queryExpression;
    }

    public void updates(List<? extends Update> updates) {
        update = Update.update(updates);
    }

    public void updates(Update... updates) {
        update = Update.update(updates);
    }

    public void setQuery(Query query) {
        where(query);
    }

    public void range(Integer begin, Integer end) {
        range(new Range(begin, end));
    }

    public void range(Range range) {
        this.range = range;
    }

    @Override
    public JsonNode getBodyJson() {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        if (projection != null) {
            node.set("projection", projection.toJson());
        }
        if (query != null) {
            node.set("query", query.toJson());
        }
        if (update != null) {
            node.set("update", update.toJson());
        }
        if (range != null) {
            range.appendToJson(node);
        }
        return node;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    @Override
    public String getOperationPathParam() {
        return "update";
    }

    @Override
    public Operation getOperation() {
        return Operation.UPDATE;
    }

}
