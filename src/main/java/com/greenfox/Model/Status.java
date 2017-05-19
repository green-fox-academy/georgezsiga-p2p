package com.greenfox.Model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * Created by georgezsiga on 5/18/17.
 */
@JsonTypeInfo(use= Id.NAME, include = As.PROPERTY, property = "status")
@JsonSubTypes({
    @JsonSubTypes.Type(value = StatusOk.class, name = "ok"),
    @JsonSubTypes.Type(value = StatusError.class, name = "error")
})
public interface Status {

}
