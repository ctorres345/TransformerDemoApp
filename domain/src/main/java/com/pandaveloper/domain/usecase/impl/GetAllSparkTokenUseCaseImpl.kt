package com.pandaveloper.domain.usecase.impl

import com.pandaveloper.domain.executor.DefaultDispatcherProvider
import com.pandaveloper.domain.executor.DispatcherProvider
import com.pandaveloper.domain.repository.TransformerRepository
import com.pandaveloper.domain.usecase.GetAllSparkTokenUseCase
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllSparkTokenUseCaseImpl(
    private val repository: TransformerRepository,
    private val dispatcherProvider: DispatcherProvider
)  : GetAllSparkTokenUseCase {
    @Inject constructor(repository: TransformerRepository) : this(repository, DefaultDispatcherProvider())

    override suspend fun execute(): String {
        return withContext(dispatcherProvider.io()) {
            repository.getAllSparkToken()
        }
    }
}