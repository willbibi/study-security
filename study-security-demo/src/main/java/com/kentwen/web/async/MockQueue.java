package com.kentwen.web.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by 毕文 on 2018/12/28.
 */

@Component
@Slf4j
public class MockQueue {

    private String placeOrder;

    private String completeOrder;


    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) throws InterruptedException {

        new Thread(() -> {
            log.info("接收到下单请求，{}", placeOrder);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOrder = placeOrder;
            log.info("下单请求处理完毕，{}" ,placeOrder);
        }).start();

//        this.placeOrder = placeOrder;
    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
