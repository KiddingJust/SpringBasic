package com.kidding.core.scan.filter;

import java.lang.annotation.*;

//Target은 클래스 레벨에 붙는 것. 필드에 붙게도 가능.
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {

}
