package com.kidding.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        //일단 둘 다 컴포넌트 스캔을 통해 빈 자동 등록 필요.
        AnnotationConfigApplicationContext ac
                = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean{

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

//    @Scope("singleton")
//    static class ClientBean2{
//
//        @Autowired
//        private ObjectProvider<PrototypeBean> prototypeBeanProvider;
//
//        public int logic(){
//            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
//            prototypeBean.addCount();
//            int count = prototypeBean.getCount();
//            return count;
//        }
//    }

    //디폴트가 singleton이므로 굳이 쓰지 않아도 됨.
//    @Scope("singleton")
//    @RequiredArgsConstructor
//    static class ClientBean1{
//        //생성 시점에 prototypeBean 주입
//        private final PrototypeBean prototypeBean;
//
//        public int logic(){
//            prototypeBean.addCount();
//            int count = prototypeBean.getCount();
//            return count;
//        }
//    }
    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }
        public int getCount(){
            return count;
        }
        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init " + this);
        }
        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
