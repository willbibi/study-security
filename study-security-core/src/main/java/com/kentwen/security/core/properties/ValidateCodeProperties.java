package com.kentwen.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by 毕文 on 2019/1/11.
 */
@Getter
@Setter
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    private SmsCodeProperties sms = new SmsCodeProperties();

}
