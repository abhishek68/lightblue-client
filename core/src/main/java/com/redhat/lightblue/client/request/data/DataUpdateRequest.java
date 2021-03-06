package com.redhat.lightblue.client.request.data;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.redhat.lightblue.client.Operation;
import com.redhat.lightblue.client.Projection;
import com.redhat.lightblue.client.Query;
import com.redhat.lightblue.client.Update;
import com.redhat.lightblue.client.http.HttpMethod;
import com.redhat.lightblue.client.request.CRUDRequest;

public class DataUpdateRequest extends CRUDRequest {

    private Projection projection;
    private Update update;
    private Query query;
    private Integer begin;
    private Integer maxResults;

    public DataUpdateRequest(String entityName, String entityVersion) {
        super(HttpMethod.POST,"update",entityName, entityVersion);
    }

    public DataUpdateRequest(String entityName) {
        this(entityName,null);
    }

    public DataUpdateRequest returns(List<? extends Projection> projection) {
        return returns(projection, null, null);
    }

    public DataUpdateRequest returns(List<? extends Projection> projection, Integer begin, Integer maxResults) {
        this.projection = Projection.project(projection);
        this.begin = begin;
        this.maxResults = maxResults;

        return this;
    }

    public DataUpdateRequest returns(Projection... projection) {
        return returns(projection, null, null);
    }

    public DataUpdateRequest returns(Projection[] projection, Integer begin, Integer maxResults) {
        this.projection = Projection.project(projection);
        this.begin = begin;
        this.maxResults = maxResults;

        return this;
    }

    public DataUpdateRequest where(Query queryExpression) {
        query = queryExpression;

        return this;
    }

    public DataUpdateRequest updates(List<? extends Update> updates) {
        update = Update.update(updates);

        return this;
    }

    public DataUpdateRequest updates(Update... updates) {
        update = Update.update(updates);

        return this;
    }

    /**
     * @see #where(Query)
     */
    @Deprecated
    public DataUpdateRequest setQuery(Query query) {
        where(query);

        return this;
    }

    @Override
    public JsonNode getBodyJson() {
        ObjectNode node = (ObjectNode) super.getBodyJson();
        if (projection != null) {
            node.set("projection", projection.toJson());
        }
        if (query != null) {
            node.set("query", query.toJson());
        }
        if (update != null) {
            node.set("update", update.toJson());
        }
        appendRangeToJson(node, begin, maxResults);
        return node;
    }

    @Override
    public Operation getOperation() {
        return Operation.UPDATE;
    }

}
