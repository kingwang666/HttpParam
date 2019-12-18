package com.wang.httpparam;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.CLASS)
public @interface PostFile {

    /**
     *the post file key. default use field.name. it is invalid when field type is map.
     */
    String key() default "";

}
