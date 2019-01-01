package com.kentwen.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by 毕文 on 2018/12/27.
 */
@Slf4j
@Component
public class TimeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.info("preHandle");
        log.info("handler name:{}", ((HandlerMethod) o).getBean().getClass().getName());
        log.info("handler method:{}", ((HandlerMethod) o).getMethod().getName());
        httpServletRequest.setAttribute("startTime", new Date().getTime());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
        log.info("time interceptor 耗时: {}", new Date().getTime() - (long) httpServletRequest.getAttribute("startTime"));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("afterCompletion");
        log.info("time interceptor 耗时: {}", new Date().getTime() - (long) httpServletRequest.getAttribute("startTime"));
        log.info("ex is {}", e);
    }
}
