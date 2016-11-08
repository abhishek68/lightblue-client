package com.redhat.lightblue.client;

import java.io.Serializable;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

/**
 * Base class for any expression part that is backed by a Json node
 */
public abstract class ExpressionPart implements JsonObj, Serializable {

    private static final long serialVersionUID=1l;
    
    protected final JsonNode node;

    protected ExpressionPart(JsonNode node) {
        this.node = node;
    }

    @Override
    public JsonNode toJson() {
        return node;
    }

    @Override
    public String toString() {
        return toJson().toString();
    }
}
