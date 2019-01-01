package com.kentwen.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * Created by 毕文 on 2018/12/27.
 */
@Slf4j
//@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("time filter");
        log.info("time filter init");
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("time filter start");
        long startTime = new Date().getTime();
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("time filter 耗时 :{}", new Date().getTime() - startTime);
        log.info("time filter finish");
    }

    @Override
    public void destroy() {
        log.info("time filter destory");
    }
}
