package com.dicquemare.aiquizzgenerator.core.ui.toasts

import androidx.compose.ui.graphics.Color
import multiplatform.network.cmptoast.ToastDuration
import multiplatform.network.cmptoast.showToast

class ToastManager {
    companion object {
        fun showDefaultToast(message: String) {
            showToast(
                message = message,
                backgroundColor = Color.White,
                textColor = Color.Black,
                duration = ToastDuration.Long,
                bottomPadding = 24
            )
        }
    }
}