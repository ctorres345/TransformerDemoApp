package com.pandaveloper.domain.usecase

import com.pandaveloper.domain.model.Transformer

/**
 * This usecase takes care of updating an existing transformer based on its id and general info
 */
interface EditTransformerUseCase {
    suspend fun execute(transformer: Transformer)
}