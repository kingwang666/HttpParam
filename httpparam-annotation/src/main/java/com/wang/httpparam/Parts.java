package com.wang.httpparam;

import java.lang.annotation.*;

/**
 * Created on 2019/6/28.
 * Author: bigwang
 * Description:
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface Parts {

    String name() default "getParts";

    boolean replace() default false;

    boolean root() default false;
}
