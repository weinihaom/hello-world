package com.wei.myannotation;

import java.lang.annotation.*;

/**
 * 相当于@service注解，将类注入容器
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyBean {
    /**
     * 存入容器的名字
     *
     * @return bean名字
     */
    String value() default "";

}
