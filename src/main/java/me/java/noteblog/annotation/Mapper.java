package me.java.noteblog.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 表明是Mybatis的Mapper
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface Mapper {

    String value() default "";
}
