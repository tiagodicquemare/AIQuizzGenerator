package com.dicquemare.aiquizzgenerator.core.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import android.os.ParcelFileDescriptor
import android.print.PageRange
import android.print.PrintAttributes
import android.print.PrintDocumentAdapter
import android.print.PrintDocumentInfo
import android.print.PrintManager
import android.print.pdf.PrintedPdfDocument
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import java.io.File
import java.io.FileOutputStream
import androidx.core.graphics.createBitmap

actual class PdfGenerator(private val context: Context) {
    actual fun generatePdf(htmlFile: String, fileName: String, onComplete: (ByteArray) -> Unit) {
        generateHtmlToPdfFile(context = context, htmlContent = htmlFile, fileName = fileName, onComplete = { file ->
            onComplete.invoke(file.readBytes())
        })
    }
}

fun generateHtmlToPdfFile(
    context: Context,
    htmlContent: String,
    fileName: String = "quiz_output.pdf",
    onComplete: (File) -> Unit
) {
    val webView = WebView(context)
    webView.settings.javaScriptEnabled = true

    webView.layoutParams = ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )

    webView.measure(
        View.MeasureSpec.makeMeasureSpec(1200, View.MeasureSpec.EXACTLY),
        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    )

    webView.layout(0, 0, webView.measuredWidth, webView.measuredHeight)

    webView.webViewClient = object : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            view?.postDelayed({
                val bitmap = createBitmap(view.measuredWidth, view.measuredHeight)
                val canvas = Canvas(bitmap)
                view.draw(canvas)

                val document = PdfDocument()
                val pageInfo = PdfDocument.PageInfo.Builder(bitmap.width, bitmap.height, 1).create()
                val page = document.startPage(pageInfo)
                page.canvas.drawBitmap(bitmap, 0f, 0f, null)
                document.finishPage(page)

                val file = File(context.cacheDir, fileName)
                document.writeTo(FileOutputStream(file))
                document.close()

                onComplete(file)
            }, 300)
        }
    }

    webView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)
}



