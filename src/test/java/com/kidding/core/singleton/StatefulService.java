package com.kidding.core.singleton;

public class StatefulService {
    private int price; //상태를 유지하는 코드

    //주문 해서 값을 저장하는 것이 의도
    public void order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
        this.price = price; //여기가 문제가 됨. 
    }

    public int getPrice(){
        return price;
    }
}
