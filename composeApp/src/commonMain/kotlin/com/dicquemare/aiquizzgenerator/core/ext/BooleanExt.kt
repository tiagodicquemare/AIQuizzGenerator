package com.dicquemare.aiquizzgenerator.core.ext

fun Boolean?.toIntOrNull(): Long? {
    return when (this) {
        true -> 1
        false -> 0
        null -> null
    }
}