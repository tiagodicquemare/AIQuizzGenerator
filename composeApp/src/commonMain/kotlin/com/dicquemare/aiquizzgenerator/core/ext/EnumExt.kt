package com.dicquemare.aiquizzgenerator.core.ext

inline fun <reified T : Enum<T>> enumValueOrNull(name: String?): T? {
    return name?.let { runCatching { enumValueOf<T>(it) }.getOrNull() }
}