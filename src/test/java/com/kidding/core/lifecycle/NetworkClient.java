package com.kidding.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient{

    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출, url =  " + url);
        connect();
        call("초기화 연결 메시지");
    }
    public void setUrl(String url){
        this.url = url;
    }
    //서비스 시작 시 호출
    public void connect(){
        System.out.println("connect: " + url);
    }
    //서비스 연결 중 호출
    public void call(String message){
        System.out.println("call: " + url + " message:  " + message);
    }
    //서비스 종료 시 호출
    public void disconnect(){
        System.out.println("close: " + url);
    }

    @PostConstruct
    public void init() {
        //스프링 의존관계 주입 끝나고 해준다.
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        disconnect();
    }
}
