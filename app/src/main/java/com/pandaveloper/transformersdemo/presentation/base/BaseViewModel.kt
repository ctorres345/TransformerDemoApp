package com.pandaveloper.transformersdemo.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel

abstract class BaseViewModel : ViewModel() {
    fun dispose() = viewModelScope.cancel()
}