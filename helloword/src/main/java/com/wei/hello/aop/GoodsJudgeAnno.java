package com.wei.hello.aop;

import java.lang.annotation.*;

/**
 * Created by weiwei on 2021/4/5.
 *
 * @author weiwei
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface GoodsJudgeAnno {
    String value() default "";
    String judgeName() default "";
}
