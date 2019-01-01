package com.kentwen.web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by 毕文 on 2018/12/27.
 */
@Aspect
@Component
@Slf4j
public class TimeAspect {

    @Around("execution(* com.kentwen.web.controller.UserController.*(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        log.info("time aspect start");
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            log.info("arg is :{}", arg);
        }
        long start = new Date().getTime();
        Object object = pjp.proceed();
        log.info("time aspect 耗时：{}", new Date().getTime() - start);
        log.info("time aspect end");
        return object;
    }

}
