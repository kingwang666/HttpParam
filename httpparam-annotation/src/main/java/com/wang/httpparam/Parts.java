package com.wang.httpparam;

import java.lang.annotation.*;

/**
 * Created on 2019/6/28.
 * Author: bigwang
 * Description: 返回List&lt;MultipartBody.Part&gt;
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface Parts {

    /**
     * 方法名称
     * @return 名称
     */
    String name() default "getParts";

    /**
     * 是否替换原有存在相同名称的方法
     * @return true 替换
     */
    boolean replace() default false;

    /**
     * 是否需要父类方法返回参数加入到请求体
     * @return true 不需要
     */
    boolean root() default false;
}
