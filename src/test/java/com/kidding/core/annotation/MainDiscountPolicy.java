package com.kidding.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
    //원래는 RateDiscountPolicy 위에 @Qualifier("mainDiscountPolicy") 를 해줬었음.
    //그런데 오타치면 컴파일 시 에러가 안남. 이렇게 등록해두면, 
    //RateDiscountPolicy에 @MainDiscountPolicy로 등록할 수 있음.
    //주입하는 곳에서도 @MainDiscountPolicy로!
}
