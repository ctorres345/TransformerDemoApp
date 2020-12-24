package com.pandaveloper.domain.usecase.impl

import com.pandaveloper.domain.executor.DefaultDispatcherProvider
import com.pandaveloper.domain.executor.DispatcherProvider
import com.pandaveloper.domain.model.Transformer
import com.pandaveloper.domain.repository.TransformerRepository
import com.pandaveloper.domain.usecase.RegisterTransformerUseCase
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterTransformerUseCaseImpl(
    private val repository: TransformerRepository,
    private val dispatcherProvider: DispatcherProvider
) : RegisterTransformerUseCase {
    @Inject constructor(repository: TransformerRepository) : this(repository, DefaultDispatcherProvider())

    override suspend fun execute(transformer: Transformer) {
        withContext(dispatcherProvider.io()) {
            repository.registerTransformer(transformer)
        }
    }
}