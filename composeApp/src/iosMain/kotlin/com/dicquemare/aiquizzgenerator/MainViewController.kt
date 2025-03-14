package com.dicquemare.aiquizzgenerator

import androidx.compose.ui.window.ComposeUIViewController
import com.dicquemare.aiquizzgenerator.core.utils.IosLogger
import com.dicquemare.aiquizzgenerator.core.utils.KMMLogger
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle


fun MainViewController() = ComposeUIViewController {
    val isDarkTheme =
        UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
                UIUserInterfaceStyle.UIUserInterfaceStyleDark

    KMMLogger.instance = IosLogger()
    App(
        darkTheme = isDarkTheme,
        dynamicColor = false,
    )
}