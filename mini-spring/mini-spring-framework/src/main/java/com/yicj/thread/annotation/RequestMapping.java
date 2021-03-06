package com.yicj.thread.annotation;

import java.lang.annotation.*;

/**
 * @author zbs
 * @date 2019/5/2
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestMapping {
    String value();
}
