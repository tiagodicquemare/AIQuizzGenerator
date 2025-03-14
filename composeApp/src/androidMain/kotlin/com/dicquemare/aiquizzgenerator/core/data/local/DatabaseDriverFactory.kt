package com.dicquemare.aiquizzgenerator.core.data.local

import app.cash.sqldelight.db.SqlDriver

import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.dicquemare.aiquizzgenerator.AppDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            schema = AppDatabase.Schema,
            context = get(),
            name = "app.db"
        )
    }
}