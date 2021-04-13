package com.wei.hello.aop;

import com.alibaba.fastjson.JSON;
import com.wei.hello.config.redis.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by weiwei on 2021/4/5.
 *
 * @author weiwei
 */
@Aspect
@Component
public class GoodsAopService {
    @Autowired
    private RedisUtil redisTemplate;


    @Pointcut(value = "@annotation(com.wei.hello.aop.GoodsJudgeAnno)")
    public void ponitCut() {
    }

    @Around("ponitCut()")
    public Object aopDemoMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("注解类型环绕通知..环绕前");
        Object rest = redisTemplate.hmGet("userName", "wwei");
        if (rest != null) {
            System.out.println("注解类型环绕通知..环绕前,缓存获取值为：" + rest.toString());
            return rest;
        } else {
            rest = pjp.proceed();
            redisTemplate.hmSet("userName", "wwei", rest);
            System.out.println("注解类型环绕通知..环绕后,存入缓存值为：" + rest.toString());
        }
        //获取拦截类
        String className = pjp.getTarget().getClass().getName();
        System.out.println("拦截类为：" + className);

        //获取拦截的方法名
        MethodSignature msig = (MethodSignature) pjp.getSignature();
        Method currentMethod = pjp.getTarget().getClass().getMethod(msig.getName(), msig.getParameterTypes());
        String methodName = currentMethod.getName();
        System.out.println("拦截方法名为：" + methodName);

        //获取拦截方法的参数
        Object[] params = pjp.getArgs();
        String param = String.valueOf(JSON.toJSON(params));
        System.out.println("拦截方法的入参参数为：" + param);

        //获取注解
        GoodsJudgeAnno annotation = currentMethod.getAnnotation(GoodsJudgeAnno.class);
        System.out.println("拦截方法注解上LogType值为：" + annotation.judgeName());
        System.out.println("注解类型环绕通知..环绕后");
        return rest;
    }

//    @Around("ponitCut()")
//    public void aopDemoMethod2(ProceedingJoinPoint pjp) throws Throwable {
//        System.out.println("注解类型环绕通知..环绕前");
//        pjp.proceed();
//        //获取拦截类
//        String className = pjp.getTarget().getClass().getName();
//        System.out.println("拦截类为：" + className);
//
//        //获取拦截的方法名
//        MethodSignature msig = (MethodSignature) pjp.getSignature();
//        Method currentMethod = pjp.getTarget().getClass().getMethod(msig.getName(), msig.getParameterTypes());
//        String methodName = currentMethod.getName();
//        System.out.println("拦截方法名为：" + methodName);
//
//        //获取拦截方法的参数
//        Object[] params = pjp.getArgs();
//        String param = String.valueOf(JSON.toJSON(params));
//        System.out.println("拦截方法的入参参数为：" + param);
//
//        //获取注解
//        GoodsJudgeAnno annotation = currentMethod.getAnnotation(GoodsJudgeAnno.class);
//        System.out.println("拦截方法注解上LogType值为：" + annotation.judgeName());
//        System.out.println("注解类型环绕通知..环绕后");
//    }
}
