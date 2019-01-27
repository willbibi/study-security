package com.kentwen.security.core.validate.code.sms;

import com.kentwen.security.core.validate.ValidateCodeProcessor;
import com.kentwen.security.core.validate.code.ValidateCode;
import com.kentwen.security.core.validate.code.ValidateCodeException;
import com.kentwen.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by 毕文 on 2019/1/17.
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor {

    @Autowired
    SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode smsCode) throws Exception {

        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");
        smsCodeSender.send(mobile, smsCode.getCode());
    }

}
