package com.pandaveloper.domain.usecase.impl

import com.pandaveloper.domain.executor.DefaultDispatcherProvider
import com.pandaveloper.domain.executor.DispatcherProvider
import com.pandaveloper.domain.model.result.TeamValidationResult
import com.pandaveloper.domain.repository.TransformerRepository
import com.pandaveloper.domain.usecase.ValidateVersusTeamsUseCase
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ValidateVersusTeamsUseCaseImpl(
    private val repository: TransformerRepository,
    private val dispatcherProvider: DispatcherProvider
) : ValidateVersusTeamsUseCase {
    @Inject constructor(repository: TransformerRepository) : this(repository, DefaultDispatcherProvider())

    override suspend fun execute(): TeamValidationResult {
        return withContext(dispatcherProvider.default()) {
            val uniqueUnits = repository.getTransformer()
            val autobots = uniqueUnits.filter { it.team == "A" }
            val decepticons = uniqueUnits.filter { it.team == "D" }
            when {
                uniqueUnits.isEmpty() -> TeamValidationResult.NoUnitsError
                autobots.isEmpty() ->  TeamValidationResult.NotEnoughAutobotsError
                decepticons.isEmpty() -> TeamValidationResult.NotEnoughDecepticonsError
                else -> TeamValidationResult.Success(autobots = autobots, decepticons = decepticons)
            }
        }
    }
}