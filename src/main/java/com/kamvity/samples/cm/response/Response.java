package com.kamvity.samples.cm.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> extends ResponseAbstract {

    private T result;
}
