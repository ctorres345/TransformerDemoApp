package com.pandaveloper.domain.usecase.impl

import com.pandaveloper.domain.executor.DefaultDispatcherProvider
import com.pandaveloper.domain.executor.DispatcherProvider
import com.pandaveloper.domain.model.result.TransformerResult
import com.pandaveloper.domain.repository.TransformerRepository
import com.pandaveloper.domain.usecase.GetRecruitableTransformersUseCase
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRecruitableTransformersUseCaseImpl(
    private val repository: TransformerRepository,
    private val dispatcherProvider: DispatcherProvider
) : GetRecruitableTransformersUseCase {
    @Inject constructor(repository: TransformerRepository) : this(repository, DefaultDispatcherProvider())

    override suspend fun execute(): TransformerResult {
        return withContext(dispatcherProvider.io()) {
            try {
                val transformerList = repository.getRecruitableTransformer()
                TransformerResult.Success(transformerList)
            } catch (ex : Exception) {
                TransformerResult.Error(ex.message ?: "Error fetching recruitable transformer list")
            }
        }
    }

}