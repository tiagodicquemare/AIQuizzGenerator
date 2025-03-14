package com.dicquemare.aiquizzgenerator.core.domain

abstract class BaseUseCase<in P, R> {
    abstract suspend fun execute(params: P): R
}
