package com.pandaveloper.transformersdemo.presentation.ui.summon

import com.pandaveloper.transformersdemo.model.SummonedUnitUIModel

sealed class SummonViewState {
    data class OnUnitSummonSuccess(val summonedUnit : SummonedUnitUIModel) : SummonViewState()
    data class OnUnitSummonError(val errorMessage: String) : SummonViewState()
}