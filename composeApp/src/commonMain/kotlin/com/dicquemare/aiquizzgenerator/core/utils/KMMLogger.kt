package com.dicquemare.aiquizzgenerator.core.utils

abstract class KMMLogger {
    abstract fun debug(message: String)
    abstract fun info(message: String)
    abstract fun warn(message: String)
    abstract fun error(message: String, throwable: Throwable? = null)

    companion object {
        lateinit var instance: KMMLogger

        fun d(message: String) = instance.debug(message)
        fun i(message: String) = instance.info(message)
        fun w(message: String) = instance.warn(message)
        fun e(message: String, throwable: Throwable? = null) = instance.error(message, throwable)
    }
}
