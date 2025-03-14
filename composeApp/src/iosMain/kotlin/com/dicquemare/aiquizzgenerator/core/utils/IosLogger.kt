package com.dicquemare.aiquizzgenerator.core.utils

import platform.Foundation.NSLog

class IosLogger : KMMLogger() {
    override fun debug(message: String) = NSLog("DEBUG: $message")
    override fun info(message: String) = NSLog("INFO: $message")
    override fun warn(message: String) = NSLog("WARN: $message")
    override fun error(message: String, throwable: Throwable?) {
        NSLog("ERROR: $message")
        throwable?.let { NSLog("Throwable: $it") }
    }
}
