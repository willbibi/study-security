package com.kentwen.security.core.validate.code.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by 毕文 on 2019/1/17.
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        log.info("向手机【{}】发送验证码 【{}】", mobile, code);
    }
}
