package com.kentwen.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by 毕文 on 2019/1/16.
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
