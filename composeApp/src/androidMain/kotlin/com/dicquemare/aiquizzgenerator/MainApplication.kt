package com.dicquemare.aiquizzgenerator

import android.app.Application
import com.dicquemare.aiquizzgenerator.feature.create_deck.di.createDeckModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(createDeckModule)
        }
    }
}