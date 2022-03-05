package com.example.demo.excpetion;

import com.example.demo.utils.MessageUtils;
import org.apache.commons.lang3.StringUtils;

public class WrongDataException extends ServiceRuntimeException {

    static final String MESSAGE_KEY = "error.auth";
    static final String MESSAGE_DETAIL = "error.auth.detail";

    public WrongDataException(Class<?> cls, Object... values){
        this(cls.getSimpleName(), values);
    }

    public WrongDataException(String name, Object... values){
        super(MESSAGE_KEY, MESSAGE_DETAIL, new String[]{name, (values != null && values.length > 0) ? StringUtils.join(values, ",") : ""});
    }

    @Override
    public String getMessage() {
        return MessageUtils.getMessage(getDetailKey(), getParams());
    }

}
