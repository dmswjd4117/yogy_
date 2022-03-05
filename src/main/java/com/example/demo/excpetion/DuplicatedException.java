package com.example.demo.excpetion;

 import com.example.demo.utils.MessageUtils;
 import org.apache.commons.lang3.StringUtils;

public class DuplicatedException extends ServiceRuntimeException {

    static final String MESSAGE_KEY = "error.duplicated";
    static final String MESSAGE_DETAIL = "error.duplicated.detail";

    public DuplicatedException(String name, Object... values){
        super(MESSAGE_KEY, MESSAGE_DETAIL, new String[]{name, (values != null && values.length > 0) ? StringUtils.join(values, ",") : ""});
    }

    public DuplicatedException(Class<?> cls, Object... values){
        super(MESSAGE_KEY, MESSAGE_DETAIL, new String[]{cls.getSimpleName(), (values != null && values.length > 0) ? StringUtils.join(values, ",") : ""});
    }

    @Override
    public String getMessage() {
        return MessageUtils.getMessage(getDetailKey(), getParams());
    }

}