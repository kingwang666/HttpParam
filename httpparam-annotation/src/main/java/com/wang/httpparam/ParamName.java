package com.wang.httpparam;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.CLASS)
public @interface ParamName {

    /**
     *the post file key. the field type is map is invalid
     * @return key name
     */
    String value() default "";

}
