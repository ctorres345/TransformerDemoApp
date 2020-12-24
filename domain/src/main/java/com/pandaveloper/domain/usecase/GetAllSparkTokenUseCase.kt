package com.pandaveloper.domain.usecase

/**
 * This use case takes care of obtaining the autorhization token for all other api calls
 */
interface GetAllSparkTokenUseCase {
    suspend fun execute() : String
}