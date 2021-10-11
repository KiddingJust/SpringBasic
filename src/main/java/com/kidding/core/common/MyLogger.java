package com.kidding.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
//@Scope(value = "request")
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    //requestURL은 나중에 setter로
    public void setRequestURL(String requestURL){
        this.requestURL = requestURL;
    }
    public void log(String message){
        System.out.println("["+uuid+"]["+requestURL+"] "+message);
    }
    @PostConstruct
    public void init(){
        //전역에 고유한 값이 생성됨.
        uuid = UUID.randomUUID().toString();
        System.out.println("["+uuid+"] request scope bean create:"+this);
    }
    //HTTP 요청 빠져나갈 때 호출되는 부분
    @PreDestroy
    public void close(){
        System.out.println("["+uuid+"] request scope bean close:"+this);
    }
}
