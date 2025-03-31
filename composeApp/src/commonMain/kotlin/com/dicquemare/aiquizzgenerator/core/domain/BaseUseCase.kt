package com.dicquemare.aiquizzgenerator.core.domain

import com.dicquemare.aiquizzgenerator.core.utils.KMMLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

abstract class BaseUseCase<in P, R> {
    suspend operator fun invoke(
        params: P,
        coroutineContext: kotlinx.coroutines.CoroutineDispatcher = Dispatchers.IO,
        onSuccess: suspend (R) -> Unit,
        onFailure: suspend (Throwable) -> Unit = {}
    ) {
        withContext(coroutineContext) {
            try {
                val result = execute(params)
                onSuccess(result)
            } catch (e: Throwable) {
                KMMLogger.e("Exception in use case ${this::class.simpleName}", e)
                onFailure(e)
            }
        }
    }

    protected abstract suspend fun execute(params: P): R
}
