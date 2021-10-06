package com.kidding.core.singleton;

public class SingletonService {

    //자기 자신을 내부에 static으로 가지고 있음
    //클래스 레벨에 올라가므로 하나만 존재하게 됨.
    //JVM 실행 시 static 영역에 new 라고 되어있는 것을 사용해 생성. instance 안에만 들어가있음.
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    //2. 프라이빗 생성자를 통해 1과 같이 외부에서 만드는 것을 막을 수 있음
    private SingletonService(){

    }
    //1. 하지만 생성자가 없다면 외부에서 이렇게 만들어짐.
    public static void main(String[] args) {
        SingletonService singletonService1 = new SingletonService();
    }
}
