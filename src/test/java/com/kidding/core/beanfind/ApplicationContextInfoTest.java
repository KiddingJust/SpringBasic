package com.kidding.core.beanfind;

import com.kidding.core.AppConfig;
import com.kidding.core.member.MemberRepository;
import com.kidding.core.member.MemberService;
import com.kidding.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        //iter + tab
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + ", object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        //iter + tab
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            //스프링 내부에서 등록한 게 아니라, 외부에서(혹은 내가) 등록한 것.
            //반대는 ROLE_INFRASTRUCTURE
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + ", object = " + bean);
            }
        }
    }

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        //MemberService가 MemberServiceImpl의 객체 인스턴스인지! (아닌듯)
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("이름 없이 타입으로 조회 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("구체 타입으로 조회 조회")
    void findBeanByName2(){
        //이런 방식은 좋지 않음. 구현에 의존한 것.
        MemberService memberService = ac.getBean(MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("빈 없는 상황")
    void findBeanByNameX(){
        //람다 로직을 실행해주었을 때 이러한 에러가 발생.
        Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));
    }

    @Test
    @DisplayName("모든 타입 조회")
    void findAllBeanByType(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for(String key:beansOfType.keySet()){
            System.out.println("key: " + key + " value: " + beansOfType.get(key));
        }
        assertThat(beansOfType.size()).isEqualTo(2);
    }
}
