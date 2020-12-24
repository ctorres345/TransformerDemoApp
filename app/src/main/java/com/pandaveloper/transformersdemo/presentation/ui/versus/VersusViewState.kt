package com.pandaveloper.transformersdemo.presentation.ui.versus

import com.pandaveloper.transformersdemo.model.BattleResultUIModel

sealed class VersusViewState {
    object NoDecepticonsError : VersusViewState()
    object NoAutobotsError : VersusViewState()
    object NoUnitsError : VersusViewState()
    object TeamValidationSuccess : VersusViewState()
    data class OnBattleResults(val result: BattleResultUIModel) : VersusViewState()
}