package com.wang.httpparam;

import java.lang.annotation.*;

/**
 * Author: wangxiaojie6
 * Date: 2019/3/28
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface Params {

    String name() default "getParams";

    boolean replace() default false;

    boolean root() default false;

}
