package com.pandaveloper.domain.usecase

import com.pandaveloper.domain.model.result.DeleteResult

/**
 * This usecase takes care of deleting an existing transformer base on its id
 */
interface DeleteTransformerUseCase {
    suspend fun execute(transformerId: String) : DeleteResult
}