package com.pandaveloper.data.repository.remote

import com.pandaveloper.data.mapper.toDomainModel
import com.pandaveloper.data.mapper.toRequestDTO
import com.pandaveloper.domain.model.result.DeleteResult
import com.pandaveloper.domain.model.Transformer
import com.pandaveloper.domain.repository.TransformerRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class TransformerRemoteRepository @Inject constructor(
    private val transformerServices: TransformerServices
) : TransformerRepository {
    override suspend fun getAllSparkToken(): String {
        return transformerServices.getToken()
    }

    override suspend fun getTransformer(): List<Transformer> {
        return transformerServices.getTransformers().result.map { it.toDomainModel() }
    }

    override suspend fun getRecruitableTransformer(): List<Transformer> {
        return transformerServices.getRecruitableTransformers().result.map { it.toDomainModel() }
    }

    override suspend fun registerTransformer(transformer: Transformer) {
        transformerServices.registerTransformer(transformer.toRequestDTO())
    }

    override suspend fun deleteTransformer(transformerId: String) : DeleteResult {
        //TODO This is a workaround for not content problem with 204 responses
        return try {
            val result = transformerServices.deleteTransformerById(transformerId)
            if (result.code() >= 400) {
                DeleteResult.Error("400 error during delete transformer operation")
            } else {
                DeleteResult.Success
            }
        } catch (t: Throwable) {
            DeleteResult.Error("Error during delete transformer operation")
        }
    }

    override suspend fun editTransformer(transformer: Transformer) {
        transformerServices.updateTransformer(transformer.toRequestDTO())
    }
}