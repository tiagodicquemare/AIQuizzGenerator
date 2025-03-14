package com.dicquemare.aiquizzgenerator

import android.app.Application
import android.content.Context
import com.dicquemare.aiquizzgenerator.core.initKoin
import com.dicquemare.aiquizzgenerator.core.utils.AndroidLogger
import com.dicquemare.aiquizzgenerator.core.utils.KMMLogger
import org.koin.dsl.module

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(listOf(
            module {
                single<Context> { this@MainApplication }
            }
        )
        )


        KMMLogger.instance = AndroidLogger()
    }
}
