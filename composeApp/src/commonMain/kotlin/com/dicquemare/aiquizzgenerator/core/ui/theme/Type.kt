package com.dicquemare.aiquizzgenerator.core.ui.theme

import aiquizzgenerator.composeapp.generated.resources.JosefinSans_Bold
import aiquizzgenerator.composeapp.generated.resources.JosefinSans_BoldItalic
import aiquizzgenerator.composeapp.generated.resources.JosefinSans_ExtraLight
import aiquizzgenerator.composeapp.generated.resources.JosefinSans_ExtraLightItalic
import aiquizzgenerator.composeapp.generated.resources.JosefinSans_Italic
import aiquizzgenerator.composeapp.generated.resources.JosefinSans_Light
import aiquizzgenerator.composeapp.generated.resources.JosefinSans_LightItalic
import aiquizzgenerator.composeapp.generated.resources.JosefinSans_Medium
import aiquizzgenerator.composeapp.generated.resources.JosefinSans_MediumItalic
import aiquizzgenerator.composeapp.generated.resources.JosefinSans_Regular
import aiquizzgenerator.composeapp.generated.resources.JosefinSans_SemiBold
import aiquizzgenerator.composeapp.generated.resources.JosefinSans_SemiBoldItalic
import aiquizzgenerator.composeapp.generated.resources.Lato_Bold
import aiquizzgenerator.composeapp.generated.resources.Lato_BoldItalic
import aiquizzgenerator.composeapp.generated.resources.Lato_Italic
import aiquizzgenerator.composeapp.generated.resources.Lato_Light
import aiquizzgenerator.composeapp.generated.resources.Lato_LightItalic
import aiquizzgenerator.composeapp.generated.resources.Lato_Regular
import aiquizzgenerator.composeapp.generated.resources.Lato_Thin
import aiquizzgenerator.composeapp.generated.resources.Lato_ThinItalic
import aiquizzgenerator.composeapp.generated.resources.Res
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font

/*val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily),
)*/

@Composable
fun JosefinSansFontFamily() = FontFamily(
    Font(Res.font.JosefinSans_Bold, weight = FontWeight.Bold),
    Font(Res.font.JosefinSans_BoldItalic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(Res.font.JosefinSans_ExtraLight, weight = FontWeight.ExtraLight),
    Font(
        Res.font.JosefinSans_ExtraLightItalic,
        weight = FontWeight.ExtraLight,
        style = FontStyle.Italic
    ),
    Font(Res.font.JosefinSans_Italic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(Res.font.JosefinSans_Light, weight = FontWeight.Light),
    Font(Res.font.JosefinSans_LightItalic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(Res.font.JosefinSans_Regular, weight = FontWeight.Normal),
    Font(Res.font.JosefinSans_Medium, weight = FontWeight.Medium),
    Font(Res.font.JosefinSans_MediumItalic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(Res.font.JosefinSans_SemiBold, weight = FontWeight.SemiBold),
    Font(
        Res.font.JosefinSans_SemiBoldItalic,
        weight = FontWeight.SemiBold,
        style = FontStyle.Italic
    ),
)

@Composable
fun LatoFontFamily() = FontFamily(
    Font(Res.font.Lato_Bold, weight = FontWeight.Bold),
    Font(Res.font.Lato_BoldItalic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(Res.font.Lato_Italic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(Res.font.Lato_Light, weight = FontWeight.Light),
    Font(Res.font.Lato_LightItalic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(Res.font.Lato_Regular, weight = FontWeight.Normal),
    Font(Res.font.Lato_Thin, weight = FontWeight.Thin),
    Font(Res.font.Lato_ThinItalic, weight = FontWeight.Thin, style = FontStyle.Italic),
)

@Composable
fun AIQuizzGeneratorTypography() = Typography().run {

    val displayFontFamily = JosefinSansFontFamily()
    val bodyFontFamily = LatoFontFamily()

    copy(
        displayLarge = displayLarge.copy(fontFamily = displayFontFamily),
        displayMedium = displayMedium.copy(fontFamily = displayFontFamily),
        displaySmall = displaySmall.copy(fontFamily = displayFontFamily),
        headlineLarge = headlineLarge.copy(fontFamily = displayFontFamily),
        headlineMedium = headlineMedium.copy(fontFamily = displayFontFamily),
        headlineSmall = headlineSmall.copy(fontFamily = displayFontFamily),
        titleLarge = titleLarge.copy(fontFamily = displayFontFamily),
        titleMedium = titleMedium.copy(fontFamily = displayFontFamily),
        titleSmall = titleSmall.copy(fontFamily = displayFontFamily),
        bodyLarge = bodyLarge.copy(fontFamily =  bodyFontFamily),
        bodyMedium = bodyMedium.copy(fontFamily = bodyFontFamily),
        bodySmall = bodySmall.copy(fontFamily = bodyFontFamily),
        labelLarge = labelLarge.copy(fontFamily = bodyFontFamily),
        labelMedium = labelMedium.copy(fontFamily = bodyFontFamily),
        labelSmall = labelSmall.copy(fontFamily = bodyFontFamily)
    )
}

