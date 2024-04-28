package com.turkcell.pair1.productservice.service.dto.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ModemConfiguration.class, name = "modem"),
        @JsonSubTypes.Type(value = InternetServiceConfiguration.class, name = "internetService")
})
public interface ProductConfiguration {
}
