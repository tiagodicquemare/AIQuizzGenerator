package com.dicquemare.aiquizzgenerator.core.utils

expect class PdfGenerator {
    fun generatePdf(htmlFile: String, fileName: String, onComplete: (ByteArray) -> Unit)
}