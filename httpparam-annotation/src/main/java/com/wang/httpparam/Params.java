package com.wang.httpparam;

import java.lang.annotation.*;

/**
 * Author: wangxiaojie6
 * Date: 2019/3/28
 * 返回Map&lt;String, String&gt;, Map&lt;String, Object&gt;, Map&lt;String, RequestBody&gt;
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface Params {

    /**
     * 方法名称
     * @return 名称
     */
    String name() default "getParams";

    /**
     * 返回的参数类型 支持Map&lt;String, String&gt;{@link ParamsType#STRING},
     * Map&lt;String, Object&gt;{@link ParamsType#OBJECT}, Map&lt;String, RequestBody&gt;{@link ParamsType#BODY}
     * @return 参数类型
     */
    ParamsType type() default ParamsType.STRING;

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
