package com.wang.httpparam;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.CLASS)
public @interface PostFile {
}
