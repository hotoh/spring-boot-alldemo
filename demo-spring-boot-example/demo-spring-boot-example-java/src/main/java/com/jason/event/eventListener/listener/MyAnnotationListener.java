package com.jason.event.eventListener.listener;

import com.jason.event.MyTestEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyAnnotationListener {

    @EventListener(classes = {MyTestEvent.class})
    public void listener1(MyTestEvent event) {
        System.out.println("注解监听器1:" + event.getMsg());
    }
}

