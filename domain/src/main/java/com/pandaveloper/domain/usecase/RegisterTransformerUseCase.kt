package com.pandaveloper.domain.usecase

import com.pandaveloper.domain.model.Transformer

/**
 * This use case takes care of register a new transformer to the api
 */
interface RegisterTransformerUseCase {
    suspend fun execute(transformer: Transformer)
}