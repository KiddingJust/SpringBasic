package com.kidding.core;

import com.kidding.core.discount.DiscountPolicy;
import com.kidding.core.discount.RateDiscountPolicy;
import com.kidding.core.member.MemberRepository;
import com.kidding.core.member.MemberService;
import com.kidding.core.member.MemberServiceImpl;
import com.kidding.core.member.MemoryMemberRepository;
import com.kidding.core.order.OrderService;
import com.kidding.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//애플리케이션에 대한 환경 설정을 담당
@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
