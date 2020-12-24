package com.pandaveloper.domain.usecase

import com.pandaveloper.domain.model.result.TeamValidationResult

/**
 * This use case takes care of validating that the current list of transformer register in the API
 * can be used for the battle operation
 */
interface ValidateVersusTeamsUseCase {
    suspend fun execute() : TeamValidationResult
}