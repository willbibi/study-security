package com.kentwen.security.core.validate.code;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by 毕文 on 2019/1/11.
 */
@Getter
@Setter
public class ValidateCode {

    private String code;

    private LocalDateTime expireTime;

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public ValidateCode(String code, long expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }

}
