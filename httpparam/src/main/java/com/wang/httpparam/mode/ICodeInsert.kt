package com.wang.httpparam.mode

/**
 * Author: wangxiaojie6
 * Date: 2019/3/28
 */
interface ICodeInsert<T> {

    fun insert(cv: T, fields: List<KField>)

}