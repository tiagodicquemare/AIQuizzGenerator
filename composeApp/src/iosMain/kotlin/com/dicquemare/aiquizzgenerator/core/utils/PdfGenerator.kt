package com.dicquemare.aiquizzgenerator.core.utils
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.refTo
import platform.Foundation.NSData
import platform.posix.memcpy

actual class PdfGenerator() {
    actual fun generatePdf(htmlFile: String, fileName: String, onComplete: (ByteArray) -> Unit) {
        /*PdfHelper.exportPdf(
            html = htmlFile,
            fileName = fileName,
            completion = { data: NSData? ->
                if (data != null) {
                    val byteArray = data.toByteArray()
                    onComplete(byteArray)
                } else {
                    onComplete(ByteArray(0)) // fallback
                }
            }
        )*/
    }
}

@OptIn(ExperimentalForeignApi::class)
fun NSData.toByteArray(): ByteArray {
    val bytes = ByteArray(this.length.toInt())
    memScoped {
        val buffer = bytes.refTo(0).getPointer(memScope)
        memcpy(buffer, this@toByteArray.bytes, this@toByteArray.length)
    }
    return bytes
}