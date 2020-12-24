package com.pandaveloper.domain.usecase.impl

import com.pandaveloper.domain.executor.DefaultDispatcherProvider
import com.pandaveloper.domain.executor.DispatcherProvider
import com.pandaveloper.domain.model.result.DeleteResult
import com.pandaveloper.domain.repository.TransformerRepository
import com.pandaveloper.domain.usecase.DeleteTransformerUseCase
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteTransformerUseCaseImpl(
    private val repository: TransformerRepository,
    private val dispatcherProvider: DispatcherProvider
) : DeleteTransformerUseCase {
    @Inject constructor(repository: TransformerRepository) : this(repository, DefaultDispatcherProvider())

    override suspend fun execute(transformerId: String) : DeleteResult {
        return withContext(dispatcherProvider.io()){
            repository.deleteTransformer(transformerId)
        }
    }
}