package com.xin.aggregateInfo.asp;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.xin.utils.Response;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

@Aspect
@Component
@EnableAspectJAutoProxy
public class MethodAspect {

    @Pointcut("@annotation(com.xin.aggregateInfo.asp.AOPAnnotations)")
    private void pointCutMethod(){}

    @Before("pointCutMethod()")
    public void cutProcess(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("注解方式AOP开始拦截, 当前拦截的方法名: " + method.getName());
    }

    @After("pointCutMethod()")
    public void after(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("注解方式AOP执行的方法 :"+method.getName()+" 执行完了");
    }
    /**
     * returning属性指定连接点方法返回的结果放置在result变量中
     * @param joinPoint 连接点
     * @param result 返回结果
     */
    @AfterReturning(value = "pointCutMethod()",returning = "result")
    public void afterReturn(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("注解方式AOP拦截的方法执行成功, 进入返回通知拦截, 方法名为: "+method.getName()+", 返回结果为: "+result.toString());
    }

    @AfterThrowing(value = "pointCutMethod()", throwing = "e")
    public void afterThrow(JoinPoint joinPoint, Exception e) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("注解方式AOP进入方法异常拦截, 方法名为: " + method.getName() + ", 异常信息为: " + e.getMessage());
    }

    @Around("pointCutMethod()")
    public Object testCutAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("注解方式AOP拦截开始进入环绕通知.......");
        Object proceed = joinPoint.proceed();
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println(arg);
        }
        System.out.println(joinPoint.proceed());
        Response response = JSON.parseObject(JSON.toJSONString(proceed), Response.class);
        if (response.getData() instanceof List){
            PageInfo pageInfo = JSON.parseObject(response.getData().toString(), PageInfo.class);
            Class<? extends List> aClass = pageInfo.getList().getClass();
            for (Field field : aClass.getFields()) {
                if (field.getType().equals(String.class)){
                    field.set(field.getName(),"你好");
                }
            }
        }
        return response;
    }


}
