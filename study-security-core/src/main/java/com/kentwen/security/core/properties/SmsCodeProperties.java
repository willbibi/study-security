package com.kentwen.security.core.properties;

import lombok.Data;

/**
 * Created by 毕文 on 2017/10/15.
 */
@Data
public class SmsCodeProperties {
    private int length = 6;
    private int expireIn = 60;
    private String url = "";

}
