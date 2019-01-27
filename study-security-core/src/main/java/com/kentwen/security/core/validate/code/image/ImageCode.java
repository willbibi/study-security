package com.kentwen.security.core.validate.code.image;

import com.kentwen.security.core.validate.code.ValidateCode;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * Created by 毕文 on 2019/1/11.
 */
@Getter
@Setter
public class ImageCode extends ValidateCode {

    private BufferedImage image;


    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;

    }

    public ImageCode(BufferedImage image, String code, long expireIn) {
        super(code, expireIn);
        this.image = image;
    }


}
