package com.kentwen.web.async;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 毕文 on 2018/12/29.
 */
@Getter
@Setter
@Component
public class DeferredResultHolder {

    private Map<String, DeferredResult<String>> map = new HashMap<>();

}
