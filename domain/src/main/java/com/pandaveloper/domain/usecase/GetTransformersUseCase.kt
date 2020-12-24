package com.pandaveloper.domain.usecase

import com.pandaveloper.domain.model.result.TransformerResult

/**
 * This use case takes care of retrieving a list of transformer units tied to a session token
 */
interface GetTransformersUseCase {
    suspend fun execute() : TransformerResult
}