package com.dicquemare.aiquizzgenerator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform