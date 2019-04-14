package com.wang.httpparam.mode
/**
 * Author: wangxiaojie6
 * Date: 2019/3/28
 */
interface ICodeInsert<T> {

    void insert(T t, List<KField> fields);

}
