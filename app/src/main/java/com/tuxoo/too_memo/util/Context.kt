package com.tuxoo.too_memo.util

import android.content.Context
import com.tuxoo.too_memo.App
import com.tuxoo.too_memo.dependency.AppComponent

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }