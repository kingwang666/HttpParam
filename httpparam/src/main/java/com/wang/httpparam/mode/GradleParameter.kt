package com.wang.httpparam.mode

import com.android.build.api.instrumentation.InstrumentationParameters
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input

/**
 * Created on 2022/4/21
 * Author: wangxiaojie
 * Description:
 */
interface GradleParameter : InstrumentationParameters {

    @get:Input
    val config: Property<GradleConfig>
}