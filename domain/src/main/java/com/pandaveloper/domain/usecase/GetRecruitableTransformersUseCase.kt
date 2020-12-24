package com.pandaveloper.domain.usecase

import com.pandaveloper.domain.model.result.TransformerResult

/**
 * This use case takes care of retrieving a list with default transformer units the user can use
 * to add to their inventory. This are outisde of the AllSpark API
 */
interface GetRecruitableTransformersUseCase {
    suspend fun execute() : TransformerResult
}