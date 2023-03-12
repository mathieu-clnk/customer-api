package com.kamvity.samples.cm.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ResponseAbstract {

    public static final String SUCCESS = "success";
    public static final String FAILED = "failed";

    private String status;

    private String errorMessage;

    private String sensitiveMessage;
}
