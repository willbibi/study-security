package com.kentwen.security.core.validate.code.sms;

import com.kentwen.security.core.properties.SecurityProperties;
import com.kentwen.security.core.validate.code.ValidateCode;
import com.kentwen.security.core.validate.code.ValidateCodeGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by 毕文 on 2019/1/16.
 */
@Slf4j
@Getter
@Setter
@Component
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        log.info("生成短信验证码:【{}】", code);
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }


}
