package com.dicquemare.aiquizzgenerator.core.utils

import android.util.Log

class AndroidLogger : KMMLogger() {
    override fun debug(message: String) { Log.d("KMMLogger", message) }
    override fun info(message: String) { Log.i("KMMLogger", message) }
    override fun warn(message: String){ Log.w("KMMLogger", message) }
    override fun error(message: String, throwable: Throwable?) { Log.e("KMMLogger", message, throwable) }
}
