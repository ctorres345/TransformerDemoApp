package com.pandaveloper.transformersdemo.presentation.ui.start

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pandaveloper.domain.usecase.GetAllSparkTokenUseCase
import com.pandaveloper.transformersdemo.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class StartViewModel @ViewModelInject constructor(
    private val getAllSparkTokenUseCase: GetAllSparkTokenUseCase
) : BaseViewModel() {
    private val viewState: MutableLiveData<StartViewState> = MutableLiveData()
    val getViewState: LiveData<StartViewState> = viewState

    fun startGame(){
        viewModelScope.launch {
            try {
                val allSparkToken = getAllSparkTokenUseCase.execute()
                viewState.value = StartViewState.OnTokenReceivedSuccess(allSparkToken)
            } catch (ex: Exception) {
                viewState.value = StartViewState.OnTokenReceivedError("Error trying to start session. Please try again")
            }
        }
    }
}