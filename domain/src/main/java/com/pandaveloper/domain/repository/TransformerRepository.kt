package com.pandaveloper.domain.repository

import com.pandaveloper.domain.model.result.DeleteResult
import com.pandaveloper.domain.model.Transformer

interface TransformerRepository {
    suspend fun getAllSparkToken() : String
    suspend fun getTransformer() : List<Transformer>
    suspend fun getRecruitableTransformer() : List<Transformer>
    suspend fun registerTransformer(transformer: Transformer)
    suspend fun deleteTransformer(transformerId: String) : DeleteResult
    suspend fun editTransformer(transformer: Transformer)
}