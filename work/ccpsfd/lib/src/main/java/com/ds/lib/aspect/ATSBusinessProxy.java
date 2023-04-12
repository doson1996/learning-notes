package com.ds.lib.aspect;

import com.ds.lib.context.SpringContext;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @author ds
 * @date 2023/4/10
 * @description 监控、灰度打标、异常处理...
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
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        // 转换为ats服务名
        String atsServerName = StringUtils.uncapitalize(className).replace("Impl", "Service");
        String methodName = joinPoint.getSignature().getName();
        Object input = joinPoint.getArgs()[0];
        String methodInfo = className + "." + methodName;
        Object result = null;
        try {
            log.info("执行{}方法, 参数: {}", methodInfo, input);
            //执行远程调用，(模拟SpringContainer中获取远程服务执行)
            Object service = SpringContext.getService(atsServerName);
            Method invokeMethod = service.getClass().getDeclaredMethod(methodName);
            result = invokeMethod.invoke(service);
        } catch (Throwable e) {
            log.error("方法名：{}，异常：", methodName, e);
        } finally {
            log.info("{}方法执行完毕, 返回结果：{}", methodName, result);
        }
        return result;
    }

}
