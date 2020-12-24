package com.pandaveloper.transformersdemo.presentation.ui.summon.dialog.customunit

import com.pandaveloper.transformersdemo.model.TransformerUIModel

sealed class CustomUnitViewState {
    data class OnUnitNameError(val errorMessage: String) : CustomUnitViewState()
    data class OnUnitTeamError(val errorMessage: String) : CustomUnitViewState()
    data class OnUnitTypeError(val errorMessage: String) : CustomUnitViewState()
    data class OnUnitCreationSuccess(val transformer: TransformerUIModel) : CustomUnitViewState()
}