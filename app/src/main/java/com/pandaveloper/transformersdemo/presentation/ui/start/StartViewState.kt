package com.pandaveloper.transformersdemo.presentation.ui.start

sealed class StartViewState {
    data class OnTokenReceivedSuccess(val token: String) : StartViewState()
    data class OnTokenReceivedError(val errorMessage: String) : StartViewState()
}