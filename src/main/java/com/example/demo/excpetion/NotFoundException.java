package com.example.demo.excpetion;

import com.example.demo.utils.MessageUtils;
import org.apache.commons.lang3.StringUtils;

public class NotFoundException extends ServiceRuntimeException {

    private static String MESSAGE_KEY = "error.notfound";
    private static String MESSAGE_DETAIL= "error.notfound.detail";

    public NotFoundException(String name, Object... values) {
        super(MESSAGE_KEY, MESSAGE_DETAIL, new String[]{name, (values != null && values.length > 0) ? StringUtils.join(values, ",") : ""});
    }

    public NotFoundException(Class<?> cls, Object... values) {
        this(cls.getSimpleName(), values);
    }

    @Override
    public String getMessage() {
        return MessageUtils.getMessage(getDetailKey(), getParams());
    }

}
