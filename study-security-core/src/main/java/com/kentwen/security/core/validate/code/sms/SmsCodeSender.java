package com.kentwen.security.core.validate.code.sms;

/**
 * Created by 毕文 on 2019/1/17.
 */
public interface SmsCodeSender {

    void send(String mobile, String code);
}
