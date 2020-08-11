package com.liang.study.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: liang
 * @Date: 2020/8/6 18:46
 */
//作用域（ElementType.METHOD 方法）
@Target(ElementType.METHOD)
//元注解（编译后文件加载位置）
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    String value() default "";
}
