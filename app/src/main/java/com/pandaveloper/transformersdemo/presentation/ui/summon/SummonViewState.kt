package com.pandaveloper.transformersdemo.presentation.ui.summon

import com.pandaveloper.transformersdemo.model.SummonedUnitUIModel

sealed class SummonViewState {
    object OnRecruitableTransformersReceived : SummonViewState()
    data class OnUnitCreationSuccess(val summonedUnit : SummonedUnitUIModel) : SummonViewState()
    data class OnUnitCreationError(val errorMessage: String) : SummonViewState()
}