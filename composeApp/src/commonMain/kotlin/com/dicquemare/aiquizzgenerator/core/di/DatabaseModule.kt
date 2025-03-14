package com.dicquemare.aiquizzgenerator.core.di

import app.cash.sqldelight.db.SqlDriver
import com.dicquemare.aiquizzgenerator.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { getDatabaseInstance(get()) }
}

fun getDatabaseInstance(driverFactory: SqlDriver): AppDatabase {
    return AppDatabase(driverFactory)
}