package com.kentwen.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by 毕文 on 2019/1/16.
 */
public interface ValidateCodeGenerator {

    ValidateCode generate(ServletWebRequest request);
}
