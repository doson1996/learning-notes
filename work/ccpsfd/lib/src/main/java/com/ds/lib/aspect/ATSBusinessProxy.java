package com.ds.lib.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author ds
 * @date 2023/4/10
 * @description
 */
@Slf4j
@Aspect
@Component
public class ATSBusinessProxy {

    @Pointcut("@annotation(com.ds.lib.annotation.AtBusiness)")
    private void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object currentObject = joinPoint.getThis();
        String methodName = joinPoint.getSignature().getName();
        Object result = null;
        try {

            log.info("执行{}方法", methodName);
            //目标方法的执行，目标方法的返回值一定要返回给外界调用者
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("方法名：{}，异常：", methodName, e);
        } finally {
            log.info("{}方法执行完毕, 返回结果：{}", methodName, result);
        }
        return result;
    }

}
