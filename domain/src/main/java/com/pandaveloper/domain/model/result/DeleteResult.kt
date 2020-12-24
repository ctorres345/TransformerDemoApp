package com.pandaveloper.domain.model.result

sealed class DeleteResult {
    object Success : DeleteResult()
    data class Error(val errorMessage: String) : DeleteResult()
}