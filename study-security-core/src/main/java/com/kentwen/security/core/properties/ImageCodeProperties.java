package com.kentwen.security.core.properties;

import lombok.Data;

/**
 * Created by 毕文 on 2017/10/15.
 */
@Data
public class ImageCodeProperties extends SmsCodeProperties {

    public ImageCodeProperties() {
        setLength(4);
    }

    private int width = 67;
    private int height = 23;
}
