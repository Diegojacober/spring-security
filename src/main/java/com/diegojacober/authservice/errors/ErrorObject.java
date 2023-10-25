package com.diegojacober.authservice.errors;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorObject {

    @JsonProperty("message")
    private final String message;

    @JsonProperty("field_name")
    private final String field;
}