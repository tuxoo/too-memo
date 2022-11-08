package com.tuxoo.too_memo

import android.app.Application
import com.tuxoo.too_memo.dependency.AppComponent
import com.tuxoo.too_memo.dependency.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .applicationContext(applicationContext)
            .build()
    }
}