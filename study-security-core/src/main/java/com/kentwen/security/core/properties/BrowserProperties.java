package com.kentwen.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by 毕文 on 2019/1/5.
 */
@Getter
@Setter
public class BrowserProperties {

    private String loginPage = "/study-signIn.html";

    private LoginType loginType = LoginType.JSON;

    private int rememberMeSeconds = 3600;
}
