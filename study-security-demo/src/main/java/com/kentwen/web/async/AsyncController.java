package com.kentwen.web.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created by 毕文 on 2018/12/28.
 */
@RestController
@Slf4j
public class AsyncController {

    @Autowired
    MockQueue mockQueue;

    @Autowired
    DeferredResultHolder deferredResultHolder;

    @GetMapping("order")
    public DeferredResult<String> order() throws InterruptedException {
        log.info("主线程开始");
        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber, result);


//        Callable<String> result = new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                log.info("副线程开始");
//                Thread.sleep(1000);
//                log.info("副线程返回");
//                return "success";
//            }
//        };
        log.info("主线程返回");
        return result;
    }

}
