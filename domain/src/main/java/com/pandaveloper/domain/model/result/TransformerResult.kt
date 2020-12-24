package com.pandaveloper.domain.model.result

import com.pandaveloper.domain.model.Transformer

sealed class TransformerResult {
    data class Success(val transformerList: List<Transformer>) : TransformerResult()
    data class Error(val errorMessage: String) : TransformerResult()
}