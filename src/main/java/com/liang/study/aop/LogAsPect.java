package com.liang.study.aop;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.lang.reflect.Method;

/**
 * @author: liang
 * @Date: 2020/8/6 17:38
 */
//aop切面类
@Aspect
//配置类
@Component
@Slf4j
public class LogAsPect {
    private static final String EDP = "execution(* com.liang.study.rabbitmq.provide.RabbitmqProductTest..*(..))";
    /**
     *切面的前置方法 即方法执行前拦截到的方法
     * 在目标方法执行之前的通知
     * @param joinPoint
     */
    @Before(EDP)
    public void doBefore(JoinPoint joinPoint){
        log.info("【RabbitmqProductTest方法执行前通知】");
    }

    /**
     在方法正常执行通过之后执行的通知叫做返回通知
     * 可以返回到方法的返回值 在注解后加入returning(出现异常不会执行)
     * @param joinPoint
     * @param result（返回值）
     */
    @AfterReturning(value = EDP,returning = "result")
    public void doAfterReturning(JoinPoint joinPoint,String result){
        log.info("【RabbitmqProductTest方法执行后通知】,result={}",result);
    }

    /**
     * 最终通知：目标方法调用之后执行的通知（无论目标方法是否出现异常均执行）
     * @param joinPoint
     */
    @After(value = EDP)
    public void doAfter(JoinPoint joinPoint){
        log.info("【RabbitmqProductTest方法执行最终通知】");
    }

    /**
     * 在目标方法非正常执行时, 抛出异常的时候会走此方法
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = EDP,throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint,Exception ex){
        log.info("【RabbitmqProductTest方法执行异常通知】");
    }

    /**
     * 环绕通知：目标方法调用前后执行的通知，可以在方法调用前后完成自定义的行为。
     * （环绕通知就是可以获取方法执行前的参数，和返回值）
     * @param joinPoint
     */
    @Around(value = EDP)
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("【RabbitmqProductTest环绕通知-方法前执行】");
        //获取方法入参
        Object[] args = joinPoint.getArgs();
        log.info("【RabbitmqProductTest环绕通知-入参】,request={}", JSONObject.toJSONString(args));
        //获取方法名
        String name = joinPoint.getSignature().getName();
        log.info("【RabbitmqProductTest环绕通知-获取方法名】,name={}", name);

        //获取返回值
        Object result = joinPoint.proceed();
        log.info("【RabbitmqProductTest环绕通知-获取返回值】,result={}", JSONObject.toJSONString(result));
        return result;
    }

    @Around(value = EDP)
    public Object doAroundController(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //IP地址
        String ipAddr = getRemoteHost(request);
        String url = request.getRequestURL().toString();
        Object[] args = joinPoint.getArgs();
        log.info("【请求源IP:【{}】,请求URL:【{}】,请求参数:【{}】】",ipAddr,url,JSONObject.toJSONString(args));
        //获取返回值
        Object result = joinPoint.proceed();
        log.info("【RabbitmqProductTest环绕通知-获取返回值】,result={}", JSONObject.toJSONString(result));
        return result;
    }

    /**
     * 获取目标主机的ip
     * @param request
     * @return
     */
    private String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    /**
     * 定位切入点为自定义注解
     * @annotation：注解
     */
    @Pointcut("@annotation(com.liang.study.aop.Log)")
    public void pointCut(){}

    @Around("pointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        Log annotation = method.getAnnotation(Log.class);
        String value = "";
        if(null!= annotation){
            //自定义注解值
            value = annotation.value();
        }
        log.info("【自定义注解aop】,注解参数={}",value);
        return joinPoint.proceed();
    }

}
