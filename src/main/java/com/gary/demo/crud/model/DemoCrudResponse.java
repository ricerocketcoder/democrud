package com.gary.demo.crud.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DemoCrudResponse {

    @JsonProperty
    @ApiModelProperty(position = 1, required = true, value = "Http status for the response", example = "HttpStatus.ACCEPTED")
    private final HttpStatus responseStatus;

    @JsonProperty
    @ApiModelProperty(position = 2, value = "Additional response message if set by the API", example = "Extra details about the response")
    private final String responseMessage;

    public DemoCrudResponse(HttpStatus status, String responseMessage) {
        this.responseStatus = status;
        this.responseMessage = responseMessage;
    }

    public DemoCrudResponse(HttpStatus status) {
        this(status, null);
    }

    public HttpStatus getResponseStatus() {
        return responseStatus;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("responseStatus", responseStatus)
                .add("responseMessage", responseMessage)
                .toString();
    }
}
