package com.dicoding.destinatik

import android.app.Application
import com.dicoding.destinatik.core.module.networkModule
import com.dicoding.destinatik.core.module.retrofitModule
import com.gagak.farmshields.core.modules.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    appModule,
                    networkModule,
                    retrofitModule
                )
            )
        }
    }
}